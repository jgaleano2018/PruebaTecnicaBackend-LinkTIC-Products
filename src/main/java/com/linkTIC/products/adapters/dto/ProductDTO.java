package com.linkTIC.products.adapters.dto;

import java.math.BigDecimal;
import java.util.UUID;


public class ProductDTO {

	private UUID id; // matches id_product INT
	private String nombre;
	private BigDecimal precio;
	private String descripcion;
	
	public ProductDTO() {}
	
	
	public ProductDTO(UUID id, String nombre, BigDecimal precio, String descripcion) {
		this.setId(id);
		this.setNombre(nombre);
		this.setPrecio(precio);
		this.setDescripcion(descripcion);
	}
	
	
	public UUID getId() {
		return id;
	}
	
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public BigDecimal getPrecio() {
		return precio;
	}
	
	
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}