package com.github.vitorialuz229.order.DTO;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

import com.github.vitorialuz229.DTO.ReviewDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProdutoDTO {
    private int id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private int stock;
    private List<String> tags;
    private List<ReviewDTO> reviews;
    private List<String> images;
}
