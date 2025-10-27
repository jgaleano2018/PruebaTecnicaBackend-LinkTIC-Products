package com.linkTIC.products.domain.model.payload;

public class ProductResponse {
    private Long id;
	private String nombre;
	private Double precio;
	private String descripcion;
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
