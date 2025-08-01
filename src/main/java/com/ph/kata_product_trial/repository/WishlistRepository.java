package com.ph.kata_product_trial.repository;

import com.ph.kata_product_trial.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    Optional<Wishlist> findOneByAccountId(String accountId);
}
