package com.ph.kata_product_trial.dto.mapper;

import com.ph.kata_product_trial.dto.ProductDto;
import com.ph.kata_product_trial.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {
    @Override
    public Product toEntity(ProductDto dto) {
        return Product.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .description(dto.getDescription())
                .image(dto.getImage())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .internalReference(dto.getInternalReference())
                .shellId(dto.getShellId())
                .inventoryStatus(dto.getInventoryStatus())
                .rating(dto.getRatings())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    @Override
    public ProductDto toDto(Product entity) {
        return ProductDto.builder()
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .image(entity.getImage())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .internalReference(entity.getInternalReference())
                .shellId(entity.getShellId())
                .inventoryStatus(entity.getInventoryStatus())
                .ratings(entity.getRating())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
