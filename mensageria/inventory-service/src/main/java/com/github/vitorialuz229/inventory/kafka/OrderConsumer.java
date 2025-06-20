package com.github.vitorialuz229.inventory.kafka;

import com.github.vitorialuz229.inventory.dto.OrderDTO;
import com.github.vitorialuz229.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "orders", groupId = "inventory-group", containerFactory = "orderKafkaListenerContainerFactory")
    public void consumeOrder(OrderDTO orderDTO) {
        inventoryService.processOrder(orderDTO);
    }
}
