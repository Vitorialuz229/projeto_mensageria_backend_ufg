package com.github.vitorialuz229.inventory.dto;

import com.github.vitorialuz229.inventory.model.InventoryEvent;
import com.github.vitorialuz229.inventory.model.InventoryItem;
import com.github.vitorialuz229.inventory.model.enums.EventStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEventDTO {

    private UUID id;
    private UUID orderId;
    private EventStatus status;
    private String message;
    private LocalDateTime eventDate;
    private List<InventoryItemDTO> items;

    public static InventoryEventDTO fromEntity(InventoryEvent event) {
        return new InventoryEventDTO(
                event.getId(),
                event.getOrderId(),
                event.getStatus(),
                event.getMessage(),
                event.getEventDate(),
                event.getItems().stream().map(item ->
                        new InventoryItemDTO(
                                item.getProdutoId(),
                                item.getQuantityRequested(),
                                item.getQuantityReserved()
                        )
                ).collect(Collectors.toList())
        );
    }
}