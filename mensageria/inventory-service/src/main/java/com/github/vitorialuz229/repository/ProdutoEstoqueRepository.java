package com.github.vitorialuz229.repository;

import com.github.vitorialuz229.model.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, UUID> {

    Optional<ProdutoEstoque> findByProdutoId(UUID produtoId);
}
