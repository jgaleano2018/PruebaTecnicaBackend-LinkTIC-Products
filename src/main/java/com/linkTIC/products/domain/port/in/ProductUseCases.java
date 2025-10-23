package com.linkTIC.products.domain.port.in;

import java.util.List;
import java.util.UUID;

import com.linkTIC.products.domain.model.Product;

public interface ProductUseCases {
	Product create(Product product);
	Product update(UUID id, Product product);
	Product getById(UUID id);
    List<Product> getAll();
    void delete(UUID id);
}