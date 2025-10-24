package com.linkTIC.products.domain.port.in;

import java.util.List;

import com.linkTIC.products.adapters.dto.ProductDTO;
import com.linkTIC.products.domain.model.Product;

public interface ProductUseCases {
	Product create(ProductDTO product);
	Product update(Long id, Product product);
	Product getById(Long id);
    List<Product> getAll();
    void delete(Long id);
}