package com.linkTIC.products.domain.port.out;

import java.util.Optional;

import com.linkTIC.products.domain.model.Purchase;

import java.util.List;

public interface PurchaseRepositoryPort {
	Purchase save(Purchase purchase);
	Optional<Purchase> findById(Long id);
    List<Purchase> findAll();
}