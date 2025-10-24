package com.linkTIC.products.domain.port.in;

import java.util.List;

import com.linkTIC.products.domain.model.Purchase;

public interface PurchaseUseCases {
	Purchase create(Purchase purchase);
	Purchase getById(Long id);
    List<Purchase> getAll();
}