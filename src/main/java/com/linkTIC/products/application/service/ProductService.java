package com.linkTIC.products.application.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkTIC.products.adapters.dto.ProductDTO;
import com.linkTIC.products.adapters.mapper.ProductMapper;
import com.linkTIC.products.domain.model.Inventory;
import com.linkTIC.products.domain.model.Product;
import com.linkTIC.products.domain.port.in.ProductUseCases;
import com.linkTIC.products.domain.port.out.ProductRepositoryPort;

@Service
public class ProductService implements ProductUseCases {
	
    private final ProductRepositoryPort repository;
    private final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final InventoryClient inventoryClient;
    
    public ProductService(ProductRepositoryPort repository, InventoryClient inventoryClient){
        this.repository = repository;
        this.inventoryClient = inventoryClient;
    }

    @Override
    @Transactional
    public Product create(ProductDTO product){
    	
    	Product saved;
    	Product productToDomain;
    	Optional<com.linkTIC.products.domain.model.Product> productOptional;
    	
    	try {
    		
    		productToDomain = ProductMapper.toDomain(product);
    		
    		saved = this.repository.save(productToDomain);
    		
    		log.info("Created Product id={} product_id={}", saved.getId(), saved.getId());
    		
    		productOptional = repository.findById(saved.getId());
    		
    		
    		inventoryClient.checkInventory(productOptional.get().getId().toString())
            .doOnError(ex -> { log.warn("Error calling inventory: ", ex.getMessage());
            		
            		if (ex.getMessage() == "Error 500") {
            			
            			Inventory inventory = new Inventory(ThreadLocalRandom.current().nextLong(), saved.getId(), product.getCantidad());
            			
            			inventoryClient.createInventory(inventory)
                        .doOnError(ex2 -> log.warn("Error calling inventory: ", ex.getMessage()))
                        .subscribe(jsonInv -> {
                            
                            
                            log.info("Created inventory id={} product_id={}", jsonInv.get("data").get("attributes").get("id").asLong(), saved.getId());
                           
                        });
            		}
            	})
            .subscribe(json -> {
                int stockInventory = json.get("data").get("attributes").get("cantidad").asInt();
                Long inventoryId = json.get("data").get("attributes").get("id").asLong();
                //System.out.println("Stock available: " + available);
                
                if (stockInventory == 0) {
                	log.warn("Error Insufficient inventory - Product id={}", productOptional.get().getId().toString());
                }
                else {
                	log.info("Inventory Product id={} Inventory id={}",  productOptional.get().getId(), inventoryId);
                }
                
            });
    		
    		
    		return saved;
    		
		
	    } catch (Exception e) {
	    	
	    	log.warn("Attempt to create Product id={}", product.getNombre());
	        throw new EntityNotFoundException("Error: "+e.getMessage());
	    }
    }

    @Override
    @Transactional
    public Product update(Long id, Product Product){
    	
    	Product existing;
    	Product saved;
    	
    	try {
	    	existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
	    	
	    	saved = this.repository.save(existing);
	    	
	    	log.info("Updated Product id={}", id);
	    	
	    	return saved;
    	  
	    } catch (Exception e) {
	    	
	    	log.warn("Attempt to update Product id={}", Product.getId());
	        throw new EntityNotFoundException("Error: "+e.getMessage());
	    }
    	
    }

    @Override
    public Product getById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<Product> getAll(){   	 
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id){
    	
    	try {
    		
    		repository.delete(id);
    		
    	} catch (Exception e) {
	    	
	    	log.warn("Attempt to delete Product id={}", id);
	        throw new EntityNotFoundException("Error: "+e.getMessage());
	    }
    }
    
    public void verifyStock(String productId) {
        inventoryClient.checkInventory(productId)
                .doOnError(ex -> System.err.println("Error calling inventory: " + ex.getMessage()))
                .subscribe(json -> {
                    boolean available = json.get("data").get("attributes").get("available").asBoolean();
                    System.out.println("Stock available: " + available);
                });
    }
}