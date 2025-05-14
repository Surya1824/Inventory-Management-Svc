package com.surya.inventory.management.svc.service;

import com.surya.inventory.management.svc.config.RabbitConfig;
import com.surya.inventory.management.svc.model.Inventory;
import com.surya.inventory.management.svc.model.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryConsumer
{
    private static final Logger logger = LoggerFactory.getLogger(InventoryConsumer.class);
    private static final String PRODUCT_UPDATE_QUEUE = "Product_Update_Queue";

    private final InventoryService inventoryService;

    public InventoryConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void orderConsumer(OrderMessage orderMessage){

        logger.info("Order message from Order Management svc: {}", orderMessage);

    }

    @RabbitListener(queues = PRODUCT_UPDATE_QUEUE)
    public void productConsumer(List<Inventory> inventoryList){
        logger.info("Inventory Details: {}", inventoryList);
        inventoryService.saveInventoryDetails(inventoryList);
    }
}
