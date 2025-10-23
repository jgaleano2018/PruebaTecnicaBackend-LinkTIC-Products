package com.linkTIC.products.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
	private UUID id; // matches id_colaborador INT
	private String nombre;
	private BigDecimal precio;
	private String descripcion;
	
	// constructors, getters, setters
	
	
	public Product() {}
	
	
	public Product(UUID id, String nombre, BigDecimal precio, String descripcion) {
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