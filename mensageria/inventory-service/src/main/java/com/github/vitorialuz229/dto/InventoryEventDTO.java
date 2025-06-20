package com.github.vitorialuz229.dto;

import com.github.vitorialuz229.model.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
}
