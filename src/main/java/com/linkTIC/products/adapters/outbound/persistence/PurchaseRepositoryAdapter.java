package com.linkTIC.products.adapters.outbound.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.linkTIC.products.adapters.outbound.persistence.repository.PurchaseRepository;
import com.linkTIC.products.domain.model.Purchase;
import com.linkTIC.products.domain.port.out.PurchaseRepositoryPort;
import com.linkTIC.products.outbound.persistence.jpa.PurchaseEntity;


@Component
public class PurchaseRepositoryAdapter implements PurchaseRepositoryPort {

    private final PurchaseRepository PurchaseRepo;

    public PurchaseRepositoryAdapter(PurchaseRepository PurchaseRepo){
        this.PurchaseRepo = PurchaseRepo;
    }

    private Purchase toDomain(PurchaseEntity e) { 
    	return new Purchase(e.getId(), e.getIdProduct(), e.getTotalPurchase(), e.getUpdatedAt(), e.getCreatedAt());
    }
    
    private PurchaseEntity toEntity(Purchase p) { 
    	PurchaseEntity e = new PurchaseEntity();    	
    	e.setId(p.getId());
    	e.setIdProduct(p.getIdProduct());
    	e.setTotalPurchase(p.getTotalPurchase());
    	e.setCreatedAt(p.getCreatedAt());
    	e.setUpdatedAt(p.getUpdatedAt());
		return e;
	}

    @Override
    public Purchase save(Purchase Purchase){
    	PurchaseEntity entity = toEntity(Purchase);
        if (entity.getId() == null) entity.setId(Purchase.getId());
        PurchaseEntity saved = PurchaseRepo.save(entity);
        return toDomain(saved);
    }
    
    @Override
	public Optional<Purchase> findById(Long id) {
		return PurchaseRepo.findById(id).map(this::toDomain);
	}
	
	
	@Override
	public List<Purchase> findAll() {
		return PurchaseRepo.findAll().stream().map(this::toDomain).collect(Collectors.toList());
	}

}
