package com.surya.inventory.management.svc.controller;

import com.surya.inventory.management.svc.exceptions.InvalidInputException;
import com.surya.inventory.management.svc.exceptions.RoleMismatchError;
import com.surya.inventory.management.svc.model.Inventory;
import com.surya.inventory.management.svc.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    public final String ADMIN_ROLE = "ADMIN";

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory-details/{id}")
    public ResponseEntity<Inventory> getInventoryDetailsById(@RequestHeader(name = "User-Type") String userType, @PathVariable(name = "id") Long id)
            throws RoleMismatchError, InvalidInputException {
        if(userType.equalsIgnoreCase(ADMIN_ROLE))
            return inventoryService.getInventoryDetailsById(id);
        else
            throw new RoleMismatchError("You do not have the required role to access this resource.");
    }

    @GetMapping("/inventory-details")
    public ResponseEntity<List<Inventory>> getInventoryDetails(@RequestHeader(name = "User-Type") String userType) throws RoleMismatchError {
        if(userType.equalsIgnoreCase(ADMIN_ROLE))
            return inventoryService.getInventoryDetails();
        else
            throw new RoleMismatchError("You do not have the required role to access this resource.");
    }

    @PostMapping("/update/inventory-details")
    public ResponseEntity<Inventory> UpdateInventoryDetails(@RequestBody Inventory inventory, @RequestHeader(name = "User-Type") String userType)
            throws RoleMismatchError {
        if(userType.equalsIgnoreCase(ADMIN_ROLE))
            return inventoryService.updateInventoryDetails(inventory);
        else
            throw new RoleMismatchError("You do not have the required role to access this resource.");
    }

}
