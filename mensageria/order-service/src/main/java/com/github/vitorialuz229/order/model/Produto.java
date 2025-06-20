package com.github.vitorialuz229.order.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.github.vitorialuz229.model.Review;

@Entity
@Table(name = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "reviews")
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
