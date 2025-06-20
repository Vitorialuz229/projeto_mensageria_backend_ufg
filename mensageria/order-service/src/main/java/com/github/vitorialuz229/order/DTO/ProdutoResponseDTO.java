package com.github.vitorialuz229.order.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import com.github.vitorialuz229.DTO.ProdutoDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProdutoResponseDTO {
    private List<ProdutoDTO> products;
    private int total;
    private int skip;
    private int limit;
}