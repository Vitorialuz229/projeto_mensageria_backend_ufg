package com.github.vitorialuz229.notification.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent {
    private String orderId;
    private String status;
    private String email;
    private String phone;
}