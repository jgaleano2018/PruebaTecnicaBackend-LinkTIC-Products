package com.linkTIC.products.adapters.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.linkTIC.products.adapters.dto.PurchaseDTO;
import com.linkTIC.products.domain.model.Purchase;
import com.linkTIC.products.domain.model.payload.PurchaseResponse;


public class PurchaseMapper {

    public static PurchaseResponse mapToUserResponse(Purchase purchase) {
    	PurchaseResponse PurchaseResponse = new PurchaseResponse();
    	PurchaseResponse.setId(purchase.getId());
    	PurchaseResponse.setIdProduct(purchase.getIdProduct());
    	PurchaseResponse.setTotalPurchase(purchase.getTotalPurchase());
        return PurchaseResponse;
    }
    
    
    public static PurchaseDTO toDto(Purchase p) {
    	if (p == null) return null;
    	PurchaseDTO dto = new PurchaseDTO();
    	dto.setId(p.getId());
    	dto.setIdProduct(p.getIdProduct());
    	dto.setTotalPurchase(p.getTotalPurchase());
    	return dto;
    }


    public static Purchase toDomain(PurchaseDTO dto) {
    	if (dto == null) return null;
    	Purchase p = new Purchase();
    	p.setId(dto.getId());
    	p.setIdProduct(dto.getIdProduct());
    	p.setTotalPurchase(dto.getTotalPurchase());
    	return p;
    }


    public static List<PurchaseDTO> toDtoList(List<Purchase> list) {
    	return list.stream().map(PurchaseMapper::toDto).collect(Collectors.toList());
    }

}