package com.github.vitorialuz229.order.DTO;

import com.github.vitorialuz229.order.DTO.OrderItemDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private UUID orderId;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> orderItems;
}