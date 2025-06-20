package com.github.vitorialuz229.order.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ReviewDTO {
    private int rating;
    private String comment;
    private String date;
    private String reviewerName;
    private String reviewerEmail;
}