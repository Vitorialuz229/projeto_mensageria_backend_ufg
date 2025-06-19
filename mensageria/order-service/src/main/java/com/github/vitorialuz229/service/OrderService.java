package com.github.vitorialuz229.service;

import com.github.vitorialuz229.kafka.OrderProducer;
import com.github.vitorialuz229.model.Order;
import com.github.vitorialuz229.model.OrderItem;
import com.github.vitorialuz229.model.Produto;
import com.github.vitorialuz229.repository.OrderRepository;
import com.github.vitorialuz229.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProdutoRepository produtoRepository;
    private final OrderProducer orderProducer;

    public Order createOrder(List<OrderItem> items) {
        for (OrderItem item : items) {
            UUID productId = item.getProduto().getId();
            Produto produto = produtoRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + productId));

            if (produto.getEstoqueQuantidade() < item.getQuantity()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setEstoqueQuantidade(produto.getEstoqueQuantidade() - item.getQuantity());
            produtoRepository.save(produto);
        }

        Order order = new Order();
        for (OrderItem item : items) {
            item.setOrder(order);
        }
        order.setOrderItems(items);

        Order savedOrder = orderRepository.save(order);

        orderProducer.sendOrder(savedOrder);

        return savedOrder;
    }
}