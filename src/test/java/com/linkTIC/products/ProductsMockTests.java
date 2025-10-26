package com.linkTIC.products;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkTIC.products.adapters.mapper.ProductMapper;
import com.linkTIC.products.adapters.outbound.persistence.ProductRepositoryAdapter;
import com.linkTIC.products.adapters.outbound.persistence.repository.ProductRepository;
import com.linkTIC.products.application.service.ProductService;
import com.linkTIC.products.domain.model.Product;
import com.linkTIC.products.outbound.persistence.jpa.ProductEntity;

@ExtendWith(MockitoExtension.class)
class ProductMockTest {

    @Mock
    private ProductRepository ProductRepository;

    @InjectMocks
    private ProductService ProductService;
    
    @Autowired
    private ProductRepository springRepo;

    @Autowired
    private ProductRepositoryAdapter adapter;

    @Test
    void shouldSaveProduct() {
    	Long productId = 1L;
    	
   	 	Product Product = new Product(productId, "ProductTest01", 50000.00, "ProductTest01");
   	 
		ProductEntity savedEntity = new ProductEntity();
		savedEntity.setNombre("ProductTest01");
		savedEntity.setPrecio(50000.00);
		savedEntity.setDescripcion("ProductTest01");
		
		 when(springRepo.save(any(ProductEntity.class))).thenReturn(savedEntity);
		
		 Product out = adapter.save(Product);
		
		 assertThat(out.getId()).isEqualTo(productId);
    }
    
    
    @Test
    void shouldUpdateProduct() {
    	Long productId = 1L;
   	 	
   	 	Product Product = new Product(productId, "ProductTest01", 50000.00, "ProductTest01");
   	 	
   	 	Product createInv = adapter.save(Product);
   	 
   	 	Optional<Product> findInv = adapter.findById(createInv.getId());
   	 	
   	 	Product ProductUpdate = ProductMapper.toDomainFromOptionalAux(findInv);
   	
   	 	ProductUpdate.setPrecio(70000.00);
   	 	
   	 	ProductEntity ProductEntityUpdate = new ProductEntity();
   	 	ProductEntityUpdate.setId(productId);
   	 	ProductEntityUpdate.setNombre("ProductTest02");
   	 	ProductEntityUpdate.setPrecio(70000.00);
   	 	ProductEntityUpdate.setDescripcion("ProductTest02");
	 	
		when(springRepo.save(any(ProductEntity.class))).thenReturn(ProductEntityUpdate);
		
		Product out = adapter.save(Product);
		 
		assertThat(out.getId()).isEqualTo(productId);
    }
}
