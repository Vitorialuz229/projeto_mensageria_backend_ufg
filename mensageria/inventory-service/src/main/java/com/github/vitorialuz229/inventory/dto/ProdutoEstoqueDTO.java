package com.github.vitorialuz229.inventory.dto;

import com.github.vitorialuz229.inventory.model.ProdutoEstoque;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEstoqueDTO {
    private UUID id;
    private UUID produtoId;
    private int quantidadeDisponivel;

    public static ProdutoEstoqueDTO fromEntity(ProdutoEstoque entity) {
        return new ProdutoEstoqueDTO(entity.getId(), entity.getProdutoId(), entity.getQuantidadeDisponivel());
    }

    public ProdutoEstoque toEntity() {
        ProdutoEstoque entity = new ProdutoEstoque();
        entity.setId(this.id);
        entity.setProdutoId(this.produtoId);
        entity.setQuantidadeDisponivel(this.quantidadeDisponivel);
        return entity;
    }
}
