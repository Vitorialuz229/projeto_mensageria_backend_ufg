package com.github.vitorialuz229.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemDTO {
    private UUID produtoId;
    private int quantityRequested;
    private int quantityReserved;
}
