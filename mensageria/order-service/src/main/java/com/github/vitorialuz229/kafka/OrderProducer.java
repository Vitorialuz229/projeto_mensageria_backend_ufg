package com.github.vitorialuz229.kafka;

import com.github.vitorialuz229.DTO.OrderDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public void sendOrder(OrderDTO orderDto) {
        kafkaTemplate.send("orders", orderDto);
    }
}