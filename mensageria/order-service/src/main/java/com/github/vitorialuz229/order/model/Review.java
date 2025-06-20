package com.github.vitorialuz229.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int rating;
    private String comment;
    private String date;
    private String reviewerName;
    private String reviewerEmail;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
