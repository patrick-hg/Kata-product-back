package com.ph.kata_product_trial.dto.factory;

import com.ph.kata_product_trial.dto.ProductDto;
import com.ph.kata_product_trial.model.InventoryStatus;

public class ProductDtoDummyFactory {

    public static ProductDto getProduct(String name, String code) {

        return ProductDto.builder()
                .name(name)
                .code(code)
                .description("This is a %s.".formatted(name))
                .price(Math.random() * 50)
                .quantity(Math.round(Math.random() * 50))
                .internalReference(code)
                .inventoryStatus(InventoryStatus.INSTOCK)
                .ratings((float) (Math.random() * 5))
                .build();
    }

}
