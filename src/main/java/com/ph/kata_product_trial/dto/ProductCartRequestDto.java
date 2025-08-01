package com.ph.kata_product_trial.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCartRequestDto {

    private String productReference;
    private int quantity;
}
