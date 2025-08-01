package com.ph.kata_product_trial.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "wishlists")
@Data
@Builder
public class Wishlist {

    @MongoId
    private String accountId;
    private List<String> products;
}
