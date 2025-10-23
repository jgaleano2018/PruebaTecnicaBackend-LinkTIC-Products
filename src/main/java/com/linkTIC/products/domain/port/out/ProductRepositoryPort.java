package com.linkTIC.products.domain.port.out;

import java.util.Optional;
import java.util.UUID;

import com.linkTIC.products.domain.model.Product;

import java.util.List;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(UUID id);
    List<Product> findAll();
    void delete(UUID id);
}