package com.github.vitorialuz229.order.service;

import com.github.vitorialuz229.order.kafka.OrderProducer;

import com.github.vitorialuz229.order.model.Order;
import com.github.vitorialuz229.order.model.Produto;
import com.github.vitorialuz229.order.model.OrderItem;

import com.github.vitorialuz229.order.DTO.OrderDTO;
import com.github.vitorialuz229.order.DTO.OrderItemDTO;

import com.github.vitorialuz229.order.repository.OrderRepository;
import com.github.vitorialuz229.order.repository.ProdutoRepository;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProdutoRepository produtoRepository;
    private final OrderProducer orderProducer;

    public OrderDTO createOrder(OrderDTO dto) {
        try {
            List<OrderItem> items = dto.getOrderItems().stream().map(orderItemDTO -> {
                Produto produto = produtoRepository.findById(orderItemDTO.getProdutoId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + orderItemDTO.getProdutoId()));

                if (produto.getEstoqueQuantidade() < orderItemDTO.getQuantity()) {
                    throw new RuntimeException("Estoque insuficiente para produto: " + produto.getNome());
                }

                produto.setEstoqueQuantidade(produto.getEstoqueQuantidade() - orderItemDTO.getQuantity());
                produtoRepository.save(produto);

                OrderItem item = new OrderItem();
                item.setProduto(produto);
                item.setQuantity(orderItemDTO.getQuantity());
                return item;
            }).toList();

            Order order = new Order();
            for (OrderItem item : items) {
                item.setOrder(order);
            }
            order.setOrderItems(items);

            Order savedOrder = orderRepository.save(order);
            OrderDTO orderDto = convertToDto(savedOrder);
            orderProducer.sendOrder(orderDto);
            return orderDto;

        } catch (Exception e) {
            log.error("Erro ao criar pedido: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao processar pedido: " + e.getMessage());
        }
    }

    public OrderDTO convertToDto(Order order) {
        List<OrderItemDTO> itemsDto = order.getOrderItems().stream()
                .map(item -> new OrderItemDTO(item.getProduto().getId(), item.getQuantity()))
                .toList();

        return new OrderDTO(order.getOrderId(), order.getOrderDate(), itemsDto);
    }
}