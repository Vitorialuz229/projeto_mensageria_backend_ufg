package com.github.vitorialuz229.inventory.dto;

import com.github.vitorialuz229.inventory.dto.OrderItemDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private UUID orderId;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> orderItems;
}
