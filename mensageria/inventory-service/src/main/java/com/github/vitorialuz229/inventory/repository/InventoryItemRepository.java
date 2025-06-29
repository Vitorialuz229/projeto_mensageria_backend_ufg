package com.github.vitorialuz229.inventory.repository;

import com.github.vitorialuz229.inventory.model.InventoryItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, UUID> {
}
