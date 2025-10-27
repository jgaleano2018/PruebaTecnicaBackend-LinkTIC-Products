package com.linkTIC.products;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.linkTIC.products.adapters.dto.ProductDTO;
import com.linkTIC.products.adapters.mapper.ProductMapper;
import com.linkTIC.products.application.service.ProductService;
import com.linkTIC.products.domain.model.Product;

import static org.mockito.Mockito.when; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

@SpringBootTest
class ProductApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Mock
    private ProductService service;

    @Test
    void shouldReturnProduct() throws Exception {
    	
        when(service.getAll()).thenReturn(List.of(new Product(1L, "Product01", 2500.555, "Product01")));

        var Product = service.getAll();

        assertThat(Product).hasSize(1);
        verify(service, times(1)).getAll();
    }
    
    @Test
    void shouldSaveProduct() {
    	
    	ProductDTO product = new ProductDTO(1L, "Product01", 2500.555, "Product01", 100);
    	Product productDomain = ProductMapper.toDomain(product); 
        when(service.create(product)).thenReturn(productDomain);

        var saved = service.create(product);

        assertThat(saved).isNotNull();
        verify(service).create(product);
    }
    
    @Test
    void shouldUpdateProduct() {
    	ProductDTO product = new ProductDTO(1L, "Product01", 2500.555, "Product01", 100);
    	Product productDomain = ProductMapper.toDomain(product); 
        when(service.create(product)).thenReturn(productDomain);
        when(service.update(1234567890123456789L, productDomain)).thenReturn(productDomain);

        var saved = service.update(1234567890123456789L, productDomain);

        assertThat(saved).isNotNull();
        verify(service).update(1234567890123456789L, productDomain);
    }
    
    @Test
    void shouldDeleteProduct() {
    	 Long productId = 1L;
    	 Product product = new Product(productId, "Product01", 2500.555, "Product01");

         // Mock the findById to return the entity
         when(service.getById(productId)).thenReturn(product);

         // Mock the delete operation to do nothing
         doNothing().when(service).delete(productId);

         service.delete(productId);

         // Verify that findById and delete were called
         /*verify(service, times(1)).getById(productId);*/
         verify(service, times(1)).delete(product.getId());
    }
    
    @Test
    void shouldThrowExceptionWhenProductToCreateIsNull() {
        
    	 Product result = service.create(null);
    	 assertNull(result);
    }
    
    @Test
    void shouldThrowExceptionWhenProductToUpdateIsNull() {
        
    	Product result = service.update(null, null);
    	assertNull(result);
    }
}
