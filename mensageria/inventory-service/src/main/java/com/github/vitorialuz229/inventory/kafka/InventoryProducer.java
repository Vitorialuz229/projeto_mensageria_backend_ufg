package com.github.vitorialuz229.inventory.kafka;

import com.github.vitorialuz229.inventory.dto.InventoryEventDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryProducer {

    @Qualifier("inventoryEventKafkaTemplate")
    private final KafkaTemplate<String, InventoryEventDTO> kafkaTemplate;

    public void sendInventoryEvent(InventoryEventDTO eventDTO) {
        kafkaTemplate.send("inventory-events", eventDTO);
    }
}
