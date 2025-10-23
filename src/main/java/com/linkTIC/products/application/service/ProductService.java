package com.linkTIC.products.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkTIC.products.domain.model.Product;
import com.linkTIC.products.domain.port.in.ProductUseCases;
import com.linkTIC.products.domain.port.out.ProductRepositoryPort;

@Service
public class ProductService implements ProductUseCases {
    private final ProductRepositoryPort repository;

    public ProductService(ProductRepositoryPort repository){
        this.repository = repository;
    }

    @Override
    @Transactional
    public Product create(Product product){
        // add domain validations here
    	
    	try {
    		
    		this.repository.save(product);
    		
		
	    } catch (Exception e) {
	    	
	        throw new IllegalArgumentException("Error: "+e.getMessage());
	    }
    	
        return product;
    }

    @Override
    @Transactional
    public Product update(UUID id, Product product){
    	Product existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    	
    	this.repository.save(existing);
    	    	
        return product;
    }

    @Override
    public Product getById(UUID id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<Product> getAll(){   	 
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public void delete(UUID id){
        repository.delete(id);
    }
}