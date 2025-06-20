package com.github.vitorialuz229.order.DTO;

import com.github.vitorialuz229.order.DTO.ReviewDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

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
