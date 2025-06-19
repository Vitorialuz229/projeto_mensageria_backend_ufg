package com.github.vitorialuz229.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

import com.github.vitorialuz229.model.Review;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String nome;

    private String descricao;

    private String category;

    private BigDecimal preco;

    @ElementCollection
    @CollectionTable(name = "produto_tags", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "tag")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_images", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "image")
    private List<String> images;

    @Column(name = "estoque_quantidade")
    private Integer estoqueQuantidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
}
