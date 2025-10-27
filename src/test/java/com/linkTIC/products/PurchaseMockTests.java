package com.linkTIC.products;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkTIC.products.adapters.outbound.persistence.PurchaseRepositoryAdapter;
import com.linkTIC.products.adapters.outbound.persistence.repository.PurchaseRepository;
import com.linkTIC.products.application.service.PurchaseService;
import com.linkTIC.products.domain.model.Purchase;
import com.linkTIC.products.outbound.persistence.jpa.PurchaseEntity;

@ExtendWith(MockitoExtension.class)
class PurchaseMockTests {

    @Mock
    private PurchaseRepository PurchaseRepository;

    @InjectMocks
    private PurchaseService PurchaseService;
    
    @Autowired
    private PurchaseRepository springRepo;

    @Autowired
    private PurchaseRepositoryAdapter adapter;

    @Test
    void shouldSavePurchase() {
    	Long PurchaseId = 1L;
    	
   	 	Purchase Purchase = new Purchase(1L, 1L, 100, LocalDateTime.now(), LocalDateTime.now());
   	 
		PurchaseEntity savedEntity = new PurchaseEntity();
		savedEntity.setIdProduct(1L);
		savedEntity.setTotalPurchase(100);
		savedEntity.setCreatedAt(LocalDateTime.now());
		savedEntity.setUpdatedAt(LocalDateTime.now());
		
  	    when(springRepo.save(any(PurchaseEntity.class))).thenReturn(savedEntity);
		
		Purchase out = adapter.save(Purchase);
		
		assertThat(out.getId()).isEqualTo(PurchaseId);
    }
    
}
