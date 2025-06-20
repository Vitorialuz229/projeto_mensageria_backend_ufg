package com.github.vitorialuz229.order.repository;

import com.github.vitorialuz229.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
