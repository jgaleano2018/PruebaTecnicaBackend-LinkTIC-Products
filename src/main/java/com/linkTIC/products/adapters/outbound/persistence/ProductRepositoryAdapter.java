package com.linkTIC.products.adapters.outbound.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.linkTIC.products.adapters.outbound.persistence.repository.ProductRepository;
import com.linkTIC.products.domain.model.Product;
import com.linkTIC.products.domain.port.out.ProductRepositoryPort;
import com.linkTIC.products.outbound.persistence.jpa.ProductEntity;


@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepo;

    public ProductRepositoryAdapter(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    private Product toDomain(ProductEntity e) { 
    	UUID newUUID = null;
    	return new Product(newUUID, e.getNombre(), e.getPrecio(), e.getDescripcion());
    }
    
    private ProductEntity toEntity(Product p) { 
    	ProductEntity e = new ProductEntity();    	
    	e.setId(p.getId());
    	e.setNombre(p.getNombre());
    	e.setPrecio(p.getPrecio());
    	e.setDescripcion(p.getDescripcion());
		return e;
	}

    @Override
    public Product save(Product product){
    	ProductEntity entity = toEntity(product);
        if (entity.getId() == null) entity.setId(product.getId());
        ProductEntity saved = productRepo.save(entity);
        return toDomain(saved);
    }

    @Override
	public Optional<Product> findById(UUID id) {
		return productRepo.findById(id).map(this::toDomain);
	}
	
	
	@Override
	public List<Product> findAll() {
		return productRepo.findAll().stream().map(this::toDomain).collect(Collectors.toList());
	}
	
    @Override public void delete(UUID id){ productRepo.deleteById(id); }
}
