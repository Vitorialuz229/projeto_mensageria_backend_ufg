package com.github.vitorialuz229.order.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderItemDTO {
    private UUID produtoId;
    private Integer quantity;

    public OrderItemDTO(UUID produtoId, Integer quantity) {
        this.produtoId = produtoId;
        this.quantity = quantity;
    }
}