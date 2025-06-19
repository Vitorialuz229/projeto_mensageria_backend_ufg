package com.github.vitorialuz229.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.vitorialuz229.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/importar")
    public ResponseEntity<Void> importar() {
        produtoService.importarProdutos();
        return ResponseEntity.ok().build();
    }
}