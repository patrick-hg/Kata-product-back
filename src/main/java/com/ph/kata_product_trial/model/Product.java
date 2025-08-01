package com.ph.kata_product_trial.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@Builder
public class Product {

    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Long quantity;
    private String internalReference;
    private Long shellId;
    private InventoryStatus inventoryStatus;
    private Float rating;
    private Long createdAt;
    private Long updatedAt;
}
