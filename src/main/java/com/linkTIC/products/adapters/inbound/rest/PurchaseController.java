package com.linkTIC.products.adapters.inbound.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import com.linkTIC.products.adapters.dto.PurchaseDTO;
import com.linkTIC.products.adapters.mapper.PurchaseMapper;
import com.linkTIC.products.domain.model.Purchase;
import com.linkTIC.products.domain.port.in.PurchaseUseCases;

import java.net.URI;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/purchase")
@Tag(name = "Purchases", description = "Manage Purchases")
@CrossOrigin(origins = "http://localhost:4200")
public class PurchaseController {

    private final PurchaseUseCases purchaseService;

    public PurchaseController(PurchaseUseCases purchaseService){
        this.purchaseService = purchaseService;
    }

    @PostMapping
    @Operation(summary = "Create Purchase by ID", description = "Returns a single Purchase")
    public ResponseEntity<PurchaseDTO> create(@Valid @RequestBody PurchaseDTO purchase){
    	
    	System.out.println("BeforEEEE saving purcahseesss....");
            	
    	Purchase created = purchaseService.create(PurchaseMapper.toDomain(purchase));
    	
    	System.out.println("After saving purcahseesss....");
    	
    	
    	PurchaseDTO createdResponse = PurchaseMapper.toDto(created);
    	
	    return ResponseEntity
	      .created(URI.create("/api/purchase/" + createdResponse.getId()))
	      .body(createdResponse);
        
    }
    
    // simple health endpoint
    @GetMapping("/health")
    public ResponseEntity<String> health() {
      return ResponseEntity.ok("OK");
    }
}