package com.surya.inventory.management.svc.controller;

import com.surya.inventory.management.svc.model.Inventory;
import com.surya.inventory.management.svc.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory-details")
    public ResponseEntity<List<Inventory>> getInventoryDetails(){
        return inventoryService.getInventoryDetails();
    }

}
