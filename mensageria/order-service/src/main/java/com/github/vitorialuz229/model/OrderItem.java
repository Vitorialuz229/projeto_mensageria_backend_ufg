package com.github.vitorialuz229.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import com.github.vitorialuz229.model.Order;

@Entity
@Table(name = "order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Produto produto;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
