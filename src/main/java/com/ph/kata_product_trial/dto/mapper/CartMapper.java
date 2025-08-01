package com.ph.kata_product_trial.dto.mapper;

import com.ph.kata_product_trial.dto.CartDto;
import com.ph.kata_product_trial.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper implements Mapper<Cart, CartDto> {
    @Override
    public Cart toEntity(CartDto dto) {
        return Cart.builder()
                .accountId(dto.getAccountId())
                .items(dto.getItems())
                .build();
    }

    @Override
    public CartDto toDto(Cart entity) {
        return CartDto.builder()
                .accountId(entity.getAccountId())
                .items(entity.getItems())
                .build();
    }
}
