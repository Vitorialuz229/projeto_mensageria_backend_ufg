package com.github.vitorialuz229.order.DTO;

import com.github.vitorialuz229.DTO.OrderItemDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderDTO {
    private UUID orderId;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> orderItems;

    public OrderDTO(UUID orderId, LocalDateTime orderDate, List<OrderItemDTO> orderItems) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }
}