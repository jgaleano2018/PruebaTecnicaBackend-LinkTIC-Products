package com.linkTIC.products.outbound.persistence.jpa;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="purchase")
public class PurchaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(nullable=false)
	private Long idProduct;
	
	@Column(nullable=false)
	private int totalPurchase;
	
	@Column(nullable=false)
	private LocalDateTime createdAt;
	
	@Column(nullable=false)
    private LocalDateTime updatedAt;
	
	
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
