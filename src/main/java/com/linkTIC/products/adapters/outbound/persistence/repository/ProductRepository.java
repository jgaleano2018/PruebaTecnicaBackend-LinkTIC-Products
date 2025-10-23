package com.linkTIC.products.adapters.outbound.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linkTIC.products.outbound.persistence.jpa.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> { 
}