package com.github.vitorialuz229.inventory.kafka;

import com.github.vitorialuz229.inventory.dto.InventoryEventDTO;
import com.github.vitorialuz229.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InventoryConsumer {

    private final InventoryService inventoryService;

    @PostConstruct
    public void init() {
        System.out.println("InventoryConsumer iniciado!");
    }

    @KafkaListener(
            topics = "inventory-events",
            groupId = "inventory-consumer-group",
            containerFactory = "inventoryEventKafkaListenerContainerFactory"
    )
    public void consumeInventoryEvent(InventoryEventDTO eventDTO) {
        inventoryService.handleInventoryEvent(eventDTO);
    }
}
