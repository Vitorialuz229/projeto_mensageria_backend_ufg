package com.github.vitorialuz229.order.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.vitorialuz229.order.service.ProdutoService;
import com.github.vitorialuz229.order.DTO.ProdutoDTO;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/")
    public ResponseEntity<List<ProdutoDTO>> listar() {
        List<ProdutoDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping("/importar")
    public ResponseEntity<Void> importar() {
        produtoService.importarProdutos();
        return ResponseEntity.ok().build();
    }
}