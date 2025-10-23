package com.linkTIC.products.domain.port.out;

import java.util.Optional;

import com.linkTIC.products.domain.model.Product;

import java.util.List;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void delete(Long id);
}