package com.surya.inventory.management.svc.repository;

import com.surya.inventory.management.svc.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
