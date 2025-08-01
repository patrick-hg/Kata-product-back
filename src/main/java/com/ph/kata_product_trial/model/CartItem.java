package com.ph.kata_product_trial.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class CartItem {

    private String productReference;
    private String name;
    private String description;
    private Double price;
    private int quantity;
}
