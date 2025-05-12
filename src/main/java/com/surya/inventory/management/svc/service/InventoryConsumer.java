package com.surya.inventory.management.svc.service;

import com.surya.inventory.management.svc.config.RabbitConfig;
import com.surya.inventory.management.svc.model.OrderMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer
{
    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void consumeOrderMessages(OrderMessage orderMessage){

        System.out.println("Order message from Order Management svc: {}"  + orderMessage);

    }
}
