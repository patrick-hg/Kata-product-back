package com.ph.kata_product_trial.rest;

import com.ph.kata_product_trial.dto.AccountDto;
import com.ph.kata_product_trial.dto.CartDto;
import com.ph.kata_product_trial.dto.ProductCartRequestDto;
import com.ph.kata_product_trial.model.Cart;
import com.ph.kata_product_trial.service.AccountService;
import com.ph.kata_product_trial.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cart")
public class CartController {

    private CartService cartService;
    private AccountService accountService;

    public CartController(CartService cartService,
                          AccountService accountService) {
        this.cartService = cartService;
        this.accountService = accountService;
    }

    /**
     * retrieve all the carts
     * access should be restricted, and only possible for admin
     * @return
     */
    @GetMapping(path = "/findAll")
    public List<CartDto> findAll() {
        return cartService.findAll();
    }

    /**
     * a user need to visualize his cart items
     * @return
     */
    @GetMapping
    public CartDto findCart() {
        AccountDto accountDto = accountService.getCurrentUserAccount();
        return cartService.getCart(accountDto.getId());
    }

    @PostMapping(path = "/add-item")
    public Cart addItemToCart(@RequestBody ProductCartRequestDto requestDto) {
        AccountDto accountDto = accountService.getCurrentUserAccount();
        return cartService.addItemToCart(accountDto.getId(), requestDto);
    }

    @PatchMapping
    public void update(@RequestBody ProductCartRequestDto requestDto) {
        AccountDto accountDto = accountService.getCurrentUserAccount();
        cartService.updateItemInCart(accountDto.getId(), requestDto);
    }

    @DeleteMapping(path = "/{productReference}")
    public void delete(@PathVariable String productReference) {
        AccountDto accountDto = accountService.getCurrentUserAccount();
        cartService.removeItemFromCart(accountDto.getId(), productReference);
    }
}
