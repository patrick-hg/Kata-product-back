package com.ph.kata_product_trial.service;

import com.ph.kata_product_trial.dto.ProductCartRequestDto;
import com.ph.kata_product_trial.dto.CartDto;
import com.ph.kata_product_trial.dto.ProductDto;
import com.ph.kata_product_trial.dto.mapper.CartMapper;
import com.ph.kata_product_trial.model.Account;
import com.ph.kata_product_trial.model.Cart;
import com.ph.kata_product_trial.model.CartItem;
import com.ph.kata_product_trial.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository,
                       ProductService productService,
                       CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartMapper = cartMapper;
    }

    public List<CartDto> findAll() {
        return cartRepository.findAll()
                .stream()
                .map(cartMapper::toDto)
                .collect(Collectors.toList());
    }

    public CartDto createCart(Account account) {
        Cart cart = Cart.builder()
                .accountId(account.getId())
                .items(Collections.emptyMap())
                .build();
        return cartMapper.toDto(cartRepository.save(cart));
    }

    public CartDto getCart(String accountId) {
        Cart cart = cartRepository.findOneByAccountId(accountId).orElseThrow();
        return cartMapper.toDto(cart);
    }

    public Cart addItemToCart(String accountId,
                              ProductCartRequestDto productCartRequestDto) {
        // retrieve the cart
        Cart cart = cartRepository.findOneByAccountId(accountId).orElseThrow();

        // retrieve the product
        ProductDto product = productService.findProductByReference(productCartRequestDto.getProductReference());

        // check if item is already in the cart and apply the sum of item quantity
        String productReference = product.getInternalReference();
        int quantity = (
                cart.getItems().containsKey(productReference)
                        ? cart.getItems().get(productReference).getQuantity()
                        : 0 ) + productCartRequestDto.getQuantity();

        // ADD the CartItem
        CartItem cartItem = mapProductDtoToCartItem(product, quantity);
        cart.getItems().put(product.getInternalReference(), cartItem);

        // persist
        return cartRepository.save(cart);
    }

    public void updateItemInCart(String accountId,
                                 ProductCartRequestDto productCartRequestDto) {
        Cart cart = cartRepository.findOneByAccountId(accountId).orElseThrow();
        String productReference = productCartRequestDto.getProductReference();
        if (cart.getItems().containsKey(productReference)) {
            cart.getItems().get(productReference).setQuantity(productCartRequestDto.getQuantity());
        }
        cartRepository.save(cart);
    }

    public void removeItemFromCart(String accountId, String productReference) {
        Cart cart = cartRepository.findOneByAccountId(accountId).orElseThrow();
        cart.getItems().remove(productReference);
        cartRepository.save(cart);
    }

    private CartItem mapProductDtoToCartItem(ProductDto productDto,
                                             int quantity) {
        return CartItem.builder()
                .productReference(productDto.getInternalReference())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .quantity(quantity)
                .build();
    }
}
