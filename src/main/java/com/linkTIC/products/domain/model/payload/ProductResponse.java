package com.linkTIC.products.domain.model.payload;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductResponse {
    private UUID id;
	private String nombre;
	private BigDecimal precio;
	private String descripcion;
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
