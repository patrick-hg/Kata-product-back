package com.ph.kata_product_trial.repository;

import com.ph.kata_product_trial.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findProductByInternalReference(String internalReference);
}
