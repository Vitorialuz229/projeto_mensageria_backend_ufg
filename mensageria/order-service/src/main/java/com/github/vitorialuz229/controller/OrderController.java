package com.github.vitorialuz229.controller;

import com.github.vitorialuz229.DTO.OrderDTO;
import com.github.vitorialuz229.model.OrderItem;
import com.github.vitorialuz229.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody List<OrderItem> items) {
        OrderDTO savedOrder = orderService.createOrder(items);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedOrder);
    }
}
