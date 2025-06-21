package com.github.vitorialuz229.inventory.kafka;

import com.github.vitorialuz229.inventory.dto.OrderDTO;
import com.github.vitorialuz229.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final InventoryService inventoryService;

    @PostConstruct
    public void init() {
        System.out.println("OrderConsumer iniciado!");
    }

    @KafkaListener(
            topics = "orders",
            groupId = "inventory-group",
            containerFactory = "orderKafkaListenerContainerFactory"
    )
    public void consumeOrder(OrderDTO orderDTO) {
        System.out.println("Recebido pedido: " + orderDTO);
        inventoryService.processOrder(orderDTO);
    }
}