package com.linkTIC.products.application.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkTIC.products.domain.model.Inventory;
import com.linkTIC.products.domain.model.Product;
import com.linkTIC.products.domain.model.Purchase;
import com.linkTIC.products.domain.port.in.PurchaseUseCases;
import com.linkTIC.products.domain.port.out.ProductRepositoryPort;
import com.linkTIC.products.domain.port.out.PurchaseRepositoryPort;


@Service
public class PurchaseService implements PurchaseUseCases {
	
    private final PurchaseRepositoryPort repository;
    private final ProductRepositoryPort productRepository;
    private final Logger log = LoggerFactory.getLogger(PurchaseService.class);
    private final InventoryClient inventoryClient;
    
    public PurchaseService(PurchaseRepositoryPort repository,  ProductRepositoryPort productRepository, InventoryClient inventoryClient){
        this.repository = repository;
        this.productRepository = productRepository;
        this.inventoryClient = inventoryClient;
    }

    @Override
    @Transactional
    public Purchase create(Purchase purchase){
    	
    	Purchase saved;
    	Purchase saved2 = new Purchase(0L, purchase.getIdProduct(), purchase.getTotalPurchase(), purchase.getCreatedAt(), purchase.getUpdatedAt());
    	
    	
    	Purchase purchaseToDomain = purchase;
    	Optional<com.linkTIC.products.domain.model.Purchase> purchaseOptional = java.util.Optional.empty();    	
    	    	
    	try {
    		
    		
    		Optional<Product> product = productRepository.findById(purchaseOptional.get().getIdProduct());
    		
    		
    		if (product != null) {
    			
    			saved = this.repository.save(purchaseToDomain);
    		
	    		inventoryClient.checkInventory(purchaseOptional.get().getIdProduct().toString())
	            .doOnError(ex -> log.warn("Error calling purchase: ", ex.getMessage()))
	            .subscribe(json -> {
	                int stockInventory = json.get("data").get("attributes").get("cantidad").asInt();
	                Long inventoryId = json.get("data").get("attributes").get("id").asLong();
	                int stockAvailable = stockInventory-purchase.getTotalPurchase();
	                
	                if (stockAvailable <= 0) {
	                	log.warn("Error Insufficient inventory - Product id={}", purchaseOptional.get().getIdProduct().toString());
	                	
	                	Purchase existing = repository.findById(saved.getId()).orElseThrow(() -> new RuntimeException("Not found"));
	                	
	                	existing.setTotalPurchase(existing.getTotalPurchase() - purchase.getTotalPurchase());        	    	
	        	    	this.repository.save(existing);
	                }
	                else {
	                	
	                	
	                	log.info("Created Purchase id={} product_id={}", purchase.getId(), purchase.getIdProduct());
	                	
	                	Inventory inventory = new Inventory(ThreadLocalRandom.current().nextLong(), purchase.getIdProduct(), stockAvailable);
	                	
	                	inventoryClient.updateInventory(inventory)
	                    .doOnError(ex -> log.warn("Error calling purchase: ", ex.getMessage()))
	                    .subscribe(jsonInv -> {
	                        int stockInventoryInv = jsonInv.get("data").get("attributes").get("cantidad").asInt();                        
	                        int stockAvailableInv = stockInventoryInv-purchase.getTotalPurchase();
	                        
	                        if (stockAvailable == stockAvailableInv) {
	                        	log.info("Inventory Product id={} Inventory id={}",  purchaseOptional.get().getId(), inventoryId);
	                        }
	                        else {
	                        	
	                        	//Emmit event
	                        	
	                        }
	                       
	                    });
	                	
	                }
	                
	            });
    		
    		}
    		else {
    			
    			log.warn("Error Product Inexistent - Product id={}", purchaseOptional.get().getIdProduct().toString());
    		}
    		
    		return saved2;
    		
		
	    } catch (Exception e) {
	    	
	    	log.warn("Attempt to create Product id={}", purchase.getIdProduct());
	        throw new EntityNotFoundException("Error: "+e.getMessage());
	    }
    }
    
    
    @Override
    public Purchase getById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<Purchase> getAll(){   	 
        return this.repository.findAll();
    }
    
  
}