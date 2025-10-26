package com.linkTIC.products.application.service;

import java.util.List;
import java.util.Optional;

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
    	
    	if (product == null) {
    		throw new IllegalArgumentException("Product to create cannot be null");
        }
    	
    	try {
    		
    		productToDomain = ProductMapper.toDomain(product);
    		
    		saved = this.repository.save(productToDomain);
    		
    		log.info("Created Product id={}", saved.getId());
    		
    		productOptional = repository.findById(saved.getId());
    		
    		
    		inventoryClient.checkInventory(productOptional.get().getId().toString())
            .doOnError(ex -> log.warn("Error calling inventory: ", ex.getMessage()))
            .subscribe(json -> {
            	
            	System.out.println("Compact JSON:\n" + json);
            	
            	int stockInventory = 0;
                Long inventoryId = (long) 0;
                
                try {

                    inventoryId = json.get("id").asLong();
                	stockInventory = json.get("cantidad").asInt();
                                    	
                }
                catch(Exception e) {
                	
                }
                
                log.info("Inventory My Value =>>> Inventory id={}", inventoryId);
                
                if (inventoryId != 0) {
	                if (stockInventory == 0) {
	                	
	                	log.warn("Error Insufficient inventory - Product id={}", productOptional.get().getId().toString());
	                }
	                else {
	                	
	                	log.info("Inventory Product id={} Inventory id={}",  productOptional.get().getId(), inventoryId);
	                }
                }
                else {
        			
                	Inventory inventory = new Inventory();                	
                	inventory.setProducto_id(saved.getId());
                	inventory.setCantidad(product.getCantidad());
                	
        			inventoryClient.createInventory(inventory)
                    .doOnError(ex2 -> log.warn("Error calling inventory: ", ex2.getMessage()))
                    .subscribe(jsonInv -> {                        
                        
                        log.info("Created inventory id={} product_id={}", jsonInv.get("id").asLong(), saved.getId());
                       
                    });
                }
                
            });
    		
    		
    		return saved;
    		
		
	    } catch (Exception e) {
	    	
	    	log.warn("Attempt to create Product id={}", product.getNombre());
	        throw new IllegalArgumentException("Error: "+e.getMessage());
	    }
    }

    @Override
    @Transactional
    public Product update(Long id, Product product){
    	
    	Product existing;
    	Product saved;
    	
    	if (product == null) {
    		throw new IllegalArgumentException("Product to update cannot be null");
        }
    	
    	try {
	    	existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
	    	
	    	saved = this.repository.save(existing);
	    	
	    	log.info("Updated Product id={}", id);
	    	
	    	return saved;
    	  
	    } catch (Exception e) {
	    	
	    	log.warn("Attempt to update Product id={}", product.getId());
	        throw new IllegalArgumentException("Error: "+e.getMessage());
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
	        throw new IllegalArgumentException("Error: "+e.getMessage());
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