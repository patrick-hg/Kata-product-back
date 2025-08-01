package com.ph.kata_product_trial.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WishlistDto {

    private String accountId;
    private List<String> products;
}
