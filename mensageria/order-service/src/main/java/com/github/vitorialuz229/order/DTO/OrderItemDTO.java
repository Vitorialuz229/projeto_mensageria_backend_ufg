package com.github.vitorialuz229.order.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private UUID produtoId;
    private Integer quantity;
}