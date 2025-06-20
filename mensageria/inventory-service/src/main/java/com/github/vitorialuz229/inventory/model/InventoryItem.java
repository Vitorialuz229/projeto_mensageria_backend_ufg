package com.github.vitorialuz229.inventory.model;

import com.github.vitorialuz229.inventory.model.InventoryEvent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "inventory_item")
@Getter
@Setter
public class InventoryItem {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID produtoId;

    @Column(nullable = false)
    private int quantityRequested;

    @Column(nullable = false)
    private int quantityReserved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_event_id")
    private InventoryEvent inventoryEvent;
}
