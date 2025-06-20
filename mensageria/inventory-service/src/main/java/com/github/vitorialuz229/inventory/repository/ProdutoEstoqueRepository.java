package com.github.vitorialuz229.inventory.repository;

import com.github.vitorialuz229.inventory.model.ProdutoEstoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, UUID> {

    Optional<ProdutoEstoque> findByProdutoId(UUID produtoId);
}
