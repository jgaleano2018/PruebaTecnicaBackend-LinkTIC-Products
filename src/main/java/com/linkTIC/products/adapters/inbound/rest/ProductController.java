package com.linkTIC.products.adapters.inbound.rest;

import org.springframework.web.bind.annotation.*;

import com.linkTIC.products.adapters.dto.ProductDTO;
import com.linkTIC.products.adapters.mapper.ProductMapper;
import com.linkTIC.products.domain.model.Product;
import com.linkTIC.products.domain.port.in.ProductUseCases;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/persons")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductUseCases productService;

    public ProductController(ProductUseCases productService){
        this.productService = productService;
    }

    @PostMapping
    public Product create(@RequestBody ProductDTO product){
        return productService.create(ProductMapper.toDomain(product));
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable UUID id){
        return productService.getById(id);
    }

    @GetMapping
    public List<ProductDTO> list(){
        return ProductMapper.toDtoList(productService.getAll());
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable UUID id, @RequestBody ProductDTO product){
        return productService.update(id, ProductMapper.toDomain(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
    	productService.delete(id);
    }
}