package com.github.vitorialuz229.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

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

    private BigDecimal preco;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "estoque_quantidade")
    private Integer estoqueQuantidade;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getEstoque_quantidade() {
        return estoque_quantidade;
    }

    public void setEstoque_quantidade(Integer estoque_quantidade) {
        this.estoque_quantidade = estoque_quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
