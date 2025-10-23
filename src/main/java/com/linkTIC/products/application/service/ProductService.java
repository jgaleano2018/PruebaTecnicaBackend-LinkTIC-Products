package com.linkTIC.products.application.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkTIC.products.domain.model.Product;
import com.linkTIC.products.domain.port.in.ProductUseCases;
import com.linkTIC.products.domain.port.out.ProductRepositoryPort;

@Service
public class ProductService implements ProductUseCases {
	
    private final ProductRepositoryPort repository;
    private final Logger log = LoggerFactory.getLogger(ProductService.class);
    
    public ProductService(ProductRepositoryPort repository){
        this.repository = repository;
    }

    @Override
    @Transactional
    public Product create(Product Product){
    	
    	Product saved;
    	
    	try {
    		
    		this.repository.save(Product);
    		
    		saved = this.repository.save(Product);
    		
    		log.info("Created Product id={} product_id={}", saved.getId(), saved.getId());
    		
    		return saved;
    		
		
	    } catch (Exception e) {
	    	
	    	log.warn("Attempt to create Product id={}", Product.getId());
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
}