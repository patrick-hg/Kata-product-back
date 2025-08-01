package com.ph.kata_product_trial.dto;

import com.ph.kata_product_trial.model.InventoryStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
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
    private Float ratings;
    private Long createdAt;
    private Long updatedAt;
}
