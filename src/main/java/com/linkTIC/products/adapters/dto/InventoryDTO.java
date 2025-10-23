package com.linkTIC.products.adapters.dto;

public class InventoryDTO {

	private Long id; // matches id_inventory INT
	private Long producto_id;
	private int cantidad;
	
	public InventoryDTO() {}
	
	
	public InventoryDTO(Long id, Long producto_id, int cantidad) {
		this.id = id;
		this.setProducto_id(producto_id);
		this.setCantidad(cantidad);
	}
	
	
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