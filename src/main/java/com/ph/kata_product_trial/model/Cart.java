package com.ph.kata_product_trial.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Map;

@Document(collection = "carts")
@Data
@Builder
public class Cart {

    @MongoId
    private String accountId;
    private Map<String, CartItem> items;
}
