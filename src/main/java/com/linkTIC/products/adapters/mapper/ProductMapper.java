package com.linkTIC.products.adapters.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.linkTIC.products.adapters.dto.ProductDTO;
import com.linkTIC.products.domain.model.Product;
import com.linkTIC.products.domain.model.payload.ProductResponse;
import com.linkTIC.products.outbound.persistence.jpa.ProductEntity;

public class ProductMapper {

    public static ProductResponse mapToUserResponse(Product product) {
    	ProductResponse productResponse = new ProductResponse();
    	productResponse.setId(product.getId());
    	productResponse.setPrecio(product.getPrecio());
    	productResponse.setDescripcion(product.getDescripcion());
        return productResponse;
    }
    
    public static ProductDTO toDto(Product p) {
    	if (p == null) return null;
    	ProductDTO dto = new ProductDTO();
    	dto.setId(p.getId());
    	dto.setNombre(p.getNombre());
    	dto.setPrecio(p.getPrecio());
    	dto.setDescripcion(p.getDescripcion());
    	return dto;
    }

    public static Product toDomain(ProductDTO dto) {
    	if (dto == null) return null;
    	Product p = new Product();
    	p.setNombre(dto.getNombre());
    	p.setPrecio(dto.getPrecio());
    	p.setDescripcion(dto.getDescripcion());
    	return p;
    }
    
    public static Product toDomainFromOptional(Optional<ProductEntity> optProduct) {
    	if (optProduct == null) return null;
    	Product i = new Product();
    	i.setId(optProduct.get().getId());
    	i.setNombre(optProduct.get().getNombre());
    	i.setPrecio(optProduct.get().getPrecio());
    	i.setDescripcion(optProduct.get().getDescripcion());
    	return i;
    }
    
    public static Product toDomainFromOptionalAux(Optional<Product> optProduct) {
    	if (optProduct == null) return null;
    	Product i = new Product();
    	i.setId(optProduct.get().getId());
    	i.setNombre(optProduct.get().getNombre());
    	i.setPrecio(optProduct.get().getPrecio());
    	i.setDescripcion(optProduct.get().getDescripcion());
    	return i;
    }


    public static List<ProductDTO> toDtoList(List<Product> list) {
    	return list.stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }

}