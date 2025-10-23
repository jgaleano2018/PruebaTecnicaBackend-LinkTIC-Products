package com.linkTIC.products;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.linkTIC.products.adapters.inbound.rest.ProductController;
import com.linkTIC.products.application.service.ProductService;
import com.linkTIC.products.domain.model.Product;

import static org.mockito.Mockito.when; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@WebMvcTest(ProductController.class)
class ProductApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
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
        Product Product = new Product(1L, "Product01", 2500.555, "Product01");
        when(service.create(Product)).thenReturn(Product);

        var saved = service.create(Product);

        assertThat(saved).isNotNull();
        verify(service).create(Product);
    }
    
    @Test
    void shouldUpdateProduct() {
        Product Product = new Product(1L, "Product01", 2500.555, "Product01");
        when(service.create(Product)).thenReturn(Product);
        when(service.update(1234567890123456789L, Product)).thenReturn(Product);

        var saved = service.update(1234567890123456789L, Product);

        assertThat(saved).isNotNull();
        verify(service).update(1234567890123456789L, Product);
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
         verify(service, times(1)).getById(productId);
         verify(service, times(1)).delete(product.getId());
    }
}
