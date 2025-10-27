package com.linkTIC.products.outbound.persistence.jpa;

import jakarta.persistence.*;

@Entity
@Table(name="inventory")
public class InventoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(nullable=false)
	private Long producto_id;
	
	@Column(nullable=false)
	private int cantidad;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProducto_id() {
		return producto_id;
	}
	public void setProducto_id(Long producto_id) {
		this.producto_id = producto_id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}	
	
}
