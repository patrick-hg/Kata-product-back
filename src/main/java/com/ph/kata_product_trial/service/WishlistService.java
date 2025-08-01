package com.ph.kata_product_trial.service;

import com.ph.kata_product_trial.dto.WishlistDto;
import com.ph.kata_product_trial.dto.mapper.WishlistMapper;
import com.ph.kata_product_trial.model.Account;
import com.ph.kata_product_trial.model.Wishlist;
import com.ph.kata_product_trial.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;

    public WishlistService(WishlistRepository wishlistRepository,
                           WishlistMapper wishlistMapper) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistMapper = wishlistMapper;
    }

    public List<WishlistDto> findAll() {
        return wishlistRepository.findAll()
                .stream()
                .map(wishlistMapper::toDto)
                .collect(Collectors.toList());
    }

    public Wishlist createWishlist(Account account) {
        Wishlist wishlist = Wishlist.builder()
                .accountId(account.getId())
                .products(Collections.emptyList())
                .build();
        return wishlistRepository.save(wishlist);
    }

    public WishlistDto getWishlist(String accountId) {
        Wishlist wishlist = wishlistRepository.findOneByAccountId(accountId).orElseThrow();
        return wishlistMapper.toDto(wishlist);
    }

    public void addItemToWishlist(String accountId,
                                  String productReference) {
        // retrieve the wishlist
        Wishlist wishlist = wishlistRepository.findOneByAccountId(accountId).orElseThrow();

        // ADD the product reference if not already in the wishlist
        if (!wishlist.getProducts().contains(productReference)) {
            wishlist.getProducts().add(productReference);
            wishlistRepository.save(wishlist);
        }
    }

    public void removeItemFromWishlist(String accountId, String productReference) {
        Wishlist wishlist = wishlistRepository.findOneByAccountId(accountId).orElseThrow();
        if (wishlist.getProducts().contains(productReference)) {
            wishlist.getProducts().remove(productReference);
            wishlistRepository.save(wishlist);
        }
    }
}
