package com.github.vitorialuz229.inventory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "produto_estoque")
@Getter
@Setter
public class ProdutoEstoque {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "produto_id", nullable = false)
    private UUID produtoId;

    @Column(nullable = false)
    private int quantidadeDisponivel;
}
