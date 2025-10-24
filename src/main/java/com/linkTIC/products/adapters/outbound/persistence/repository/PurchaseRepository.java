package com.linkTIC.products.adapters.outbound.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.linkTIC.products.outbound.persistence.jpa.PurchaseEntity;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> { 
}