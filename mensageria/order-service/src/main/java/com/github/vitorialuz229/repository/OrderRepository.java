package com.github.vitorialuz229.repository;

import com.github.vitorialuz229.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
