package com.ph.kata_product_trial.repository;

import com.ph.kata_product_trial.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {

    Optional<Cart> findOneByAccountId(String accountId);
}
