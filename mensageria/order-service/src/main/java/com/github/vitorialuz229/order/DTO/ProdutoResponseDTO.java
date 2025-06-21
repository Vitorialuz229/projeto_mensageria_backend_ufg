package com.github.vitorialuz229.order.DTO;

import com.github.vitorialuz229.order.DTO.ProdutoDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {
    private List<ProdutoDTO> products;
    private int total;
    private int skip;
    private int limit;
}