package com.github.vitorialuz229.inventory.service;

import com.github.vitorialuz229.inventory.dto.OrderDTO;
import com.github.vitorialuz229.inventory.dto.InventoryEventDTO;
import com.github.vitorialuz229.inventory.dto.OrderItemDTO;

import com.github.vitorialuz229.inventory.model.enums.EventStatus;

import com.github.vitorialuz229.inventory.kafka.InventoryProducer;

import com.github.vitorialuz229.inventory.model.InventoryEvent;
import com.github.vitorialuz229.inventory.model.InventoryItem;
import com.github.vitorialuz229.inventory.model.ProdutoEstoque;

import com.github.vitorialuz229.inventory.repository.InventoryEventRepository;
import com.github.vitorialuz229.inventory.repository.ProdutoEstoqueRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProdutoEstoqueRepository estoqueRepository;
    private final InventoryEventRepository eventRepository;
    private final InventoryProducer producer;

    public void handleInventoryEvent(InventoryEventDTO eventDTO) {
        System.out.println("Recebido inventory-event:");
        System.out.println(eventDTO);

        //repassar para outro serviço
    }

    @Transactional
    public void processOrder(OrderDTO orderDTO) {
        boolean success = true;
        StringBuilder message = new StringBuilder();
        List<InventoryItem> items = new ArrayList<>();

        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            UUID produtoId = itemDTO.getProdutoId();
            int quantidadeSolicitada = itemDTO.getQuantity();

            ProdutoEstoque estoque = estoqueRepository.findByProdutoId(produtoId)
                    .orElse(null);

            InventoryItem item = new InventoryItem();
            item.setProdutoId(produtoId);
            item.setQuantityRequested(quantidadeSolicitada);

            if (estoque == null || estoque.getQuantidadeDisponivel() < quantidadeSolicitada) {
                item.setQuantityReserved(0);
                message.append("Produto ")
                        .append(produtoId)
                        .append(" sem estoque suficiente. ");
                success = false;
            } else {
                estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() - quantidadeSolicitada);
                estoqueRepository.save(estoque);
                item.setQuantityReserved(quantidadeSolicitada);
            }

            items.add(item);
        }

        InventoryEvent event = new InventoryEvent();
        event.setOrderId(orderDTO.getOrderId());
        event.setStatus(success ? EventStatus.RESERVED : EventStatus.FAILED);
        event.setMessage(success ? "Estoque reservado com sucesso." : message.toString().trim());
        event.setEventDate(LocalDateTime.now());

        for (InventoryItem item : items) {
            item.setInventoryEvent(event);
        }

        event.setItems(items);
        eventRepository.save(event);

        InventoryEventDTO eventDTO = InventoryEventDTO.fromEntity(event);
        producer.sendInventoryEvent(eventDTO);
    }
}