package com.github.vitorialuz229.model;

import com.github.vitorialuz229.model.enums.EventStatus;
import com.github.vitorialuz229.model.InventoryItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "inventory_event")
@Getter
@Setter
public class InventoryEvent {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status;

    private String message;

    private LocalDateTime eventDate;

    @OneToMany(mappedBy = "inventoryEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryItem> items;
}
