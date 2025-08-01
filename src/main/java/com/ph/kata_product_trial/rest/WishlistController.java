package com.ph.kata_product_trial.rest;

import com.ph.kata_product_trial.dto.AccountDto;
import com.ph.kata_product_trial.dto.WishlistDto;
import com.ph.kata_product_trial.service.AccountService;
import com.ph.kata_product_trial.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/wishlist")
public class WishlistController {

    private WishlistService wishlistService;
    private AccountService accountService;

    public WishlistController(WishlistService wishlistService,
                              AccountService accountService) {
        this.wishlistService = wishlistService;
        this.accountService = accountService;
    }

    /**
     * retrieve all the wishlists
     * access should be restricted, and only possible for admin
     * @return
     */
    @GetMapping(path = "/findAll")
    public List<WishlistDto> findAll() {
        return wishlistService.findAll();
    }

    /**
     * a user need to visualize his wishlist items
     * @return
     */
    @GetMapping
    public WishlistDto findWishlist() {
        AccountDto accountDto = accountService.getCurrentUserAccount();
        return wishlistService.getWishlist(accountDto.getId());
    }

    @PostMapping(path = "/add-item/{productReference}")
    public ResponseEntity addItemToWishlist(@PathVariable String productReference) {
        AccountDto accountDto = accountService.getCurrentUserAccount();
        wishlistService.addItemToWishlist(accountDto.getId(), productReference);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{productReference}")
    public void delete(@PathVariable String productReference) {
        AccountDto accountDto = accountService.getCurrentUserAccount();
        wishlistService.removeItemFromWishlist(accountDto.getId(), productReference);
    }
}
