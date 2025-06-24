package com.github.vitorialuz229.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vitorialuz229.notification.dto.InventoryEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final EmailService emailService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "inventory-events", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeInventoryEvent(String message) {
        try {
            InventoryEvent event = objectMapper.readValue(message, InventoryEvent.class);

            System.out.println("Evento recebido: orderId=" + event.getOrderId() + ", status=" + event.getStatus());

            if ("RESERVED".equalsIgnoreCase(event.getStatus())) {
                String assunto = "Status do Pedido: " + event.getStatus();
                String corpo = "Seu pedido <strong>" + event.getOrderId() + "</strong> está com status: <strong>" + event.getStatus() + "</strong>.";
                emailService.enviarEmail(event.getEmail(), assunto, corpo);
                System.out.println("E-mail enviado para: " + event.getEmail() + " com status: " + event.getStatus());
            }

        } catch (Exception e) {
            System.err.println("Erro ao processar evento: " + e.getMessage());
        }
    }
}
