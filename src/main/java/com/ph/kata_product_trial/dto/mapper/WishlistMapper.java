package com.ph.kata_product_trial.dto.mapper;

import com.ph.kata_product_trial.dto.WishlistDto;
import com.ph.kata_product_trial.model.Wishlist;
import org.springframework.stereotype.Component;

@Component
public class WishlistMapper implements Mapper<Wishlist, WishlistDto> {
    @Override
    public Wishlist toEntity(WishlistDto dto) {
        return Wishlist.builder()
                .accountId(dto.getAccountId())
                .products(dto.getProducts())
                .build();
    }

    @Override
    public WishlistDto toDto(Wishlist entity) {
        return WishlistDto.builder()
                .accountId(entity.getAccountId())
                .products(entity.getProducts())
                .build();
    }
}
