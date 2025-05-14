package com.surya.inventory.management.svc.service;

import com.surya.inventory.management.svc.model.Inventory;
import com.surya.inventory.management.svc.model.OperationEnum;
import com.surya.inventory.management.svc.repository.InventoryRepository;
import org.springframework.stereotype.Service;

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
                repository.save(inventory);
            } else if(operationEnum.equals(OperationEnum.REMOVE) ){
               //Operation DELETE
               repository.deleteById(inventory.getProductId());
            }

        }

    }
}
