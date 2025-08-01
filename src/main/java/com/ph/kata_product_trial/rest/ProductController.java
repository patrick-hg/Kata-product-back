package com.ph.kata_product_trial.rest;

import com.ph.kata_product_trial.dto.ProductDto;
import com.ph.kata_product_trial.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAllProducts();
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PostMapping(path = "/{productId}")
    public ProductDto update(@PathVariable String productId, @RequestBody ProductDto productDto) {

        return productService.updateProduct(productId, productDto);
    }

    @DeleteMapping(path = "/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String productId) {
        productService.deleteProduct(productId);
    }
}
