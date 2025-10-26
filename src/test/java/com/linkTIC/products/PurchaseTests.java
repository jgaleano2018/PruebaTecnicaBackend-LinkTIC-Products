package com.linkTIC.products;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.linkTIC.products.adapters.dto.PurchaseDTO;
import com.linkTIC.products.adapters.mapper.PurchaseMapper;
import com.linkTIC.products.application.service.PurchaseService;
import com.linkTIC.products.domain.model.Purchase;

import static org.mockito.Mockito.when; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

@SpringBootTest
class PurchaseTests {

	@Test
	void contextLoads() {
	}
	
	@Mock
    private PurchaseService service;

	@Test
    void shouldReturnPurchase() throws Exception {
    	
        when(service.getAll()).thenReturn(List.of(new Purchase(1L, 1L, 100, LocalDateTime.now(), LocalDateTime.now())));
        
        var Purchase = service.getAll();

        assertThat(Purchase).hasSize(1);
        verify(service, times(1)).getAll();
    }
    
    @Test
    void shouldSavePurchase() {
    	
    	PurchaseDTO Purchase = new PurchaseDTO(1L, 1L, 100, LocalDateTime.now(), LocalDateTime.now());
    	Purchase purchaseDomain = PurchaseMapper.toDomain(Purchase); 
        when(service.create(purchaseDomain)).thenReturn(purchaseDomain);

        var saved = service.create(purchaseDomain);

        assertThat(saved).isNotNull();
        verify(service).create(purchaseDomain);
    }
    
    @Test
    void shouldThrowExceptionWhenPurchaseToCreateIsNull() {
        
    	 Purchase result = service.create(null);
    	 assertNull(result);
    }
    
    
}
