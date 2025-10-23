package com.linkTIC.products.domain.model;

public class Product {
	private Long id; // matches id_colaborador INT
	private String nombre;
	private Double precio;
	private String descripcion;
	
	// constructors, getters, setters
	
	
	public Product() {}
	
	
	public Product(Long id, String nombre, Double precio, String descripcion) {
		this.setId(id);
		this.setNombre(nombre);
		this.setPrecio(precio);
		this.setDescripcion(descripcion);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	
	
}