package com.github.vitorialuz229.order.repository;

import com.github.vitorialuz229.order.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
