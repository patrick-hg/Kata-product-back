package com.ph.kata_product_trial.service;

import com.ph.kata_product_trial.dto.ProductDto;
import com.ph.kata_product_trial.dto.mapper.ProductMapper;
import com.ph.kata_product_trial.model.Product;
import com.ph.kata_product_trial.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.ph.kata_product_trial.dto.factory.ProductDtoDummyFactory.getProduct;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    private final Logger LOG = LoggerFactory.getLogger(ProductService.class);


    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> findAllProducts() {
        return productMapper.toDtos(productRepository.findAll());
    }

    public ProductDto findProductByReference(String productReference) {
        Product product = productRepository.findProductByInternalReference(productReference).orElseThrow();
        return productMapper.toDto(product);
    }

    public ProductDto createProduct(ProductDto productDto) {
        LOG.info("Creating a new instance of product {name: {}}",
                productDto.getName());
        Product product = productMapper.toEntity(productDto);
        product.setCreatedAt(new Date().toInstant().getEpochSecond());

        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    public ProductDto updateProduct(String productId, ProductDto productDto) {
        LOG.info("Updating an existent instance of product {name: {}, id: {}}",
                productDto.getName(), productId);
        Product product = productRepository.findById(productId).orElseThrow();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setInventoryStatus(productDto.getInventoryStatus());
        product.setQuantity(productDto.getQuantity());
        product.setUpdatedAt(new Date().toInstant().getEpochSecond());
        return productMapper.toDto(productRepository.save(product));
    }

    public void deleteProduct(String productId) {
        LOG.info("Deleting an instance of product {id: {}}", productId);
        productRepository.deleteById(productId);
    }

    public List<ProductDto> populateProductsWithDummyData() {
        productRepository.deleteAll();
        List<ProductDto> products = List.of(
                getProduct("Sun glasses", "sun-gla-10"),
                getProduct("Vaccum cleaner", "vaccum-cl-15"),
                getProduct("Earphones", "earph-20"),
                getProduct("Desk chair", "desk-chair-25"),
                getProduct("Round table", "table-round-30")
        );

        List<Product> persistedProducts = productRepository.saveAll(productMapper.toEntities(products));
        return productMapper.toDtos(persistedProducts);
    }
}
