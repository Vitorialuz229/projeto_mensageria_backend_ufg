package com.github.vitorialuz229.repository;

import com.github.vitorialuz229.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
