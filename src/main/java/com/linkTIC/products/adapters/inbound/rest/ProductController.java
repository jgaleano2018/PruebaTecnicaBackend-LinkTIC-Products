package com.linkTIC.products.adapters.inbound.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import com.linkTIC.products.adapters.dto.ProductDTO;
import com.linkTIC.products.adapters.mapper.ProductMapper;
import com.linkTIC.products.domain.model.Product;
import com.linkTIC.products.domain.port.in.ProductUseCases;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/Product")
@Tag(name = "Products", description = "Manage products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductUseCases ProductService;

    public ProductController(ProductUseCases ProductService){
        this.ProductService = ProductService;
    }

    @PostMapping
    @Operation(summary = "Create Product by ID", description = "Returns a single Product")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO product){
            	
    	Product created = ProductService.create(ProductMapper.toDomain(product));
    	ProductDTO createdResponse = ProductMapper.toDto(created);
    	
	    return ResponseEntity
	      .created(URI.create("/api/Product/" + createdResponse.getId()))
	      .body(createdResponse);
        
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Product by ID", description = "Returns a single Product")
    public ResponseEntity<ProductDTO> get(@PathVariable Long id){   	
        return ResponseEntity.ok(ProductMapper.toDto(ProductService.getById(id)));
    }

    @GetMapping
    @Operation(summary = "Get list Product", description = "Returns a list Product")
    public ResponseEntity<List<ProductDTO>> list(){
        return ResponseEntity.ok(ProductMapper.toDtoList(ProductService.getAll()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Product by ID", description = "Returns a single Product")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO product){
        return ResponseEntity.ok(ProductMapper.toDto(ProductService.update(id, ProductMapper.toDomain(product))));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product by ID", description = "Returns empty")
    public ResponseEntity<Void> delete(@PathVariable Long id){
    	ProductService.delete(id);
    	return ResponseEntity.noContent().build();
    }
    
    // simple health endpoint
    @GetMapping("/health")
    public ResponseEntity<String> health() {
      return ResponseEntity.ok("OK");
    }
}