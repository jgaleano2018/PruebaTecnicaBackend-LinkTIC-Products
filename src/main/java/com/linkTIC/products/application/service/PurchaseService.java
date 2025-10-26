package com.linkTIC.products.application.service;

import java.util.List;
import java.util.Optional;

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
    	
    	if (purchase == null) {
    		throw new IllegalArgumentException("Purchase to create cannot be null");
        }
    	
    	try {
    		
    		Optional<Product> product = productRepository.findById(purchase.getIdProduct());
    		
    		saved = this.repository.save(purchase);
    		
    		if (product != null) {
    		
	    		inventoryClient.checkInventory(purchase.getIdProduct().toString())
	            .doOnError(ex -> log.warn("Error calling purchase: ", ex.getMessage()))
	            .subscribe(json -> {
	            	
	                try {

	                    Long inventoryId = json.get("id").asLong();
	                	int stockInventory = json.get("cantidad").asInt();
	                	int stockAvailable = stockInventory-purchase.getTotalPurchase();
	                                    	
		                if (stockAvailable <= 0) {
		                	
		                	log.warn("Error Insufficient inventory - Product id={}", purchase.getIdProduct().toString());
		                	
		                	Purchase existing = repository.findById(saved.getId()).orElseThrow(() -> new RuntimeException("Not found"));
		                	
		                	existing.setTotalPurchase(existing.getTotalPurchase() - purchase.getTotalPurchase());        	    	
		        	    	this.repository.save(existing);
		                }
		                else {
		                	
		                	
		                	log.info("Created Purchase id={} product_id={}", saved.getId(), purchase.getIdProduct());
		                	
		                	Inventory inventory = new Inventory();		                	
		                	inventory.setId(inventoryId);
		                	inventory.setProducto_id(purchase.getIdProduct());
		                	inventory.setCantidad(stockAvailable);		                	
		                	
		                	inventoryClient.updateInventory(inventory)
		                    .doOnError(ex -> log.warn("Error calling purchase: ", ex.getMessage()))
		                    .subscribe(jsonInv -> {
		                        int stockInventoryInv = jsonInv.get("cantidad").asInt();                        
		                        int stockAvailableInv = stockInventoryInv-purchase.getTotalPurchase();
		                        
		                        if (stockAvailable == stockAvailableInv) {
		                        	log.info("Inventory Product id={} Inventory id={}",  saved.getId(), inventoryId);
		                        }
		                        else {
		                        	
		                        	//Emmit event
		                        	
		                        	log.info("Inventory Changed Product id={} Inventory id={}",  saved.getId(), inventoryId);
		                        	
		                        }
		                       
		                    });
		                	
		                }
	                
	                }
	                catch(Exception e) {
	                	
	                }
	                
	            });
    		
    		}
    		else {
    			
    			log.warn("Error Product Inexistent - Product id={}", purchase.getIdProduct().toString());
    		}
    		
    		return saved;
    		
		
	    } catch (Exception e) {
	    	
	    	log.warn("Attempt to create Product id={}", purchase.getIdProduct());
	        throw new IllegalArgumentException("Error: "+e.getMessage());
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