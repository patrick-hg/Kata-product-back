package com.ph.kata_product_trial.dto;

import com.ph.kata_product_trial.model.CartItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CartDto {

    private String accountId;
    private Map<String, CartItem> items;
}
