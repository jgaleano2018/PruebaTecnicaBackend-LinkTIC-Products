package com.linkTIC.products.adapters.dto;

public class ProductDTO {

	private Long id; // matches id_product INT
	private String nombre;
	private Double precio;
	private String descripcion;
	
	public ProductDTO() {}
	
	
	public ProductDTO(Long id, String nombre, Double precio, String descripcion) {
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