package com.github.vitorialuz229.inventory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemDTO {
    private UUID produtoId;
    private int quantityRequested;
    private int quantityReserved;
}
