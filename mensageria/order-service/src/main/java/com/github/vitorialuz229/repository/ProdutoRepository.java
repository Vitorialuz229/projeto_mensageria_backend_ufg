package com.github.vitorialuz229.repository;

import com.github.vitorialuz229.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
