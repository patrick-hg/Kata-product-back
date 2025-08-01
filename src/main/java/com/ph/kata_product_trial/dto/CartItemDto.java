package com.ph.kata_product_trial.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDto {

    private String productReference;
    private String name;
    private String description;
    private Double price;
    private int quantity;
}
