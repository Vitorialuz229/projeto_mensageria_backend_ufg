package com.github.vitorialuz229.order.kafka;

import com.github.vitorialuz229.DTO.OrderDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    @Qualifier("orderDtoKafkaTemplate")
    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public void sendOrder(OrderDTO orderDTO) {
        kafkaTemplate.send("orders", orderDTO);
    }
}