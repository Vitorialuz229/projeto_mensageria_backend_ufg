package com.github.vitorialuz229.order.service;

import com.github.vitorialuz229.order.DTO.ProdutoDTO;
import com.github.vitorialuz229.order.DTO.ProdutoResponseDTO;
import com.github.vitorialuz229.order.DTO.ReviewDTO;

import com.github.vitorialuz229.order.model.Produto;
import com.github.vitorialuz229.order.model.Review;

import com.github.vitorialuz229.order.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public void importarProdutos() {
        RestTemplate restTemplate = new RestTemplate();
        ProdutoResponseDTO response = restTemplate.getForObject(
                "https://dummyjson.com/products", ProdutoResponseDTO.class);

        if (response != null && response.getProducts() != null) {
            List<Produto> produtos = response.getProducts().stream().map(dto -> {
                Produto produto = new Produto();
                produto.setNome(dto.getTitle());
                produto.setDescricao(dto.getDescription());
                produto.setCategory(dto.getCategory());
                produto.setPreco(BigDecimal.valueOf(dto.getPrice()));
                produto.setEstoqueQuantidade(dto.getStock());

                produto.setTags(dto.getTags() != null ? dto.getTags() : List.of());
                produto.setImages(dto.getImages() != null ? dto.getImages() : List.of());

                List<Review> reviews = dto.getReviews() != null ? dto.getReviews().stream().map(reviewDTO -> {
                    Review review = new Review();
                    review.setRating(reviewDTO.getRating());
                    review.setComment(reviewDTO.getComment());
                    review.setDate(reviewDTO.getDate());
                    review.setReviewerName(reviewDTO.getReviewerName());
                    review.setReviewerEmail(reviewDTO.getReviewerEmail());
                    review.setProduto(produto);
                    return review;
                }).collect(Collectors.toList()) : List.of();

                produto.setReviews(reviews);
                return produto;
            }).collect(Collectors.toList());

            try {
                produtoRepository.saveAll(produtos);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }


}
