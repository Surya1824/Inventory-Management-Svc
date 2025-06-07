package com.surya.inventory.management.svc.service;

import com.surya.inventory.management.svc.exceptions.InvalidInputException;
import com.surya.inventory.management.svc.model.Inventory;
import com.surya.inventory.management.svc.model.OperationEnum;
import com.surya.inventory.management.svc.repository.InventoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public void saveInventoryDetails(List<Inventory> inventoryList) {

        for(Inventory inventory : inventoryList){

            OperationEnum operationEnum = inventory.getOperationEnum();

            if(operationEnum.equals(OperationEnum.CREATED) || operationEnum.equals(OperationEnum.UPDATE)){
                inventory.setUpdatedAt(LocalDateTime.now());
                repository.save(inventory);
            } else if(operationEnum.equals(OperationEnum.REMOVE) ){
               //Operation DELETE
               repository.deleteById(inventory.getProductId());
            }

        }

    }

    public ResponseEntity<List<Inventory>> getInventoryDetails() {
        List<Inventory> inventories = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(inventories);
    }

    public ResponseEntity<Inventory> updateInventoryDetails(Inventory inventory) {
        Inventory savedInventory = repository.save(inventory);
        return ResponseEntity.status(HttpStatus.OK).body(savedInventory);
    }

    public ResponseEntity<Inventory> getInventoryDetailsById(Long id) throws InvalidInputException {
        Optional<Inventory> inventoryById = repository.findById(id);
        if(inventoryById.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(inventoryById.get());
        else
            throw new InvalidInputException("Inventory Details not found, Please check Inventory Id and Retry..");
    }
}
