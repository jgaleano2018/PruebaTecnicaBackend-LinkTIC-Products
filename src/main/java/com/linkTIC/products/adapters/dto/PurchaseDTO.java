package com.linkTIC.products.adapters.dto;

import java.time.LocalDateTime;

public class PurchaseDTO {
	private Long id; // matches id_purchase INT
	private Long idProduct;
	private int totalPurchase;
	private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	
	// constructors, getters, setters
	
	
	public PurchaseDTO() {}
	
	
	public PurchaseDTO(Long id, Long idProduct, int totalPurchase, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.setId(id);
		this.setIdProduct(id);
		this.setTotalPurchase(totalPurchase);
		this.setUpdatedAt(updatedAt);
		this.setCreatedAt(createdAt);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdProduct() {
		return idProduct;
	}


	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}


	public int getTotalPurchase() {
		return totalPurchase;
	}


	public void setTotalPurchase(int totalPurchase) {
		this.totalPurchase = totalPurchase;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	

	
	
}