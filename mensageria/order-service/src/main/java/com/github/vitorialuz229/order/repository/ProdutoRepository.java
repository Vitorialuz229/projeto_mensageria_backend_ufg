package com.github.vitorialuz229.order.repository;

import com.github.vitorialuz229.order.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
