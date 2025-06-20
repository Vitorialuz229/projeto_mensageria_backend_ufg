package com.github.vitorialuz229.inventory.controller;

import com.github.vitorialuz229.inventory.dto.OrderDTO;
import com.github.vitorialuz229.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Endpoint opcional para testar recebimento de eventos.
     */
    @PostMapping("/event")
    public ResponseEntity<String> receiveEvent(@RequestBody OrderDTO orderDTO) {
        inventoryService.processOrder(orderDTO);
        return ResponseEntity.ok("Pedido processado via endpoint.");
    }
}