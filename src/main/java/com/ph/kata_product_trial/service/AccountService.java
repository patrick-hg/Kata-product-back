package com.ph.kata_product_trial.service;

import com.ph.kata_product_trial.auth.RegistrationDto;
import com.ph.kata_product_trial.dto.AccountDto;
import com.ph.kata_product_trial.dto.mapper.AccountMapper;
import com.ph.kata_product_trial.dto.mapper.AccountToRegistrationDtoMapper;
import com.ph.kata_product_trial.model.Account;
import com.ph.kata_product_trial.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private UserDetailsServiceImpl userDetailsService;
    private ProductService productService;
    private CartService cartService;
    private WishlistService wishlistService;
    private AccountMapper accountMapper;
    private AccountToRegistrationDtoMapper accountToRegistrationDtoMapper;


    public AccountService(AccountRepository accountRepository,
                          UserDetailsServiceImpl userDetailsService,
                          ProductService productService,
                          CartService cartService,
                          WishlistService wishlistService,
                          AccountMapper accountMapper,
                          AccountToRegistrationDtoMapper accountToRegistrationDtoMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.accountToRegistrationDtoMapper = accountToRegistrationDtoMapper;
        this.cartService = cartService;
        this.productService = productService;
        this.userDetailsService = userDetailsService;
        this.wishlistService = wishlistService;
    }

    public AccountDto getCurrentUserAccount() {
        UserDetails user = userDetailsService.getCurrentUser();
        return findAccountByEmail(user.getUsername()).orElseThrow();
    }

    public Optional<AccountDto> findAccountByEmail(String email) {
        return accountRepository.findByEmail(email)
                .map(account -> accountMapper.toDto(account));
    }

    public AccountDto createAccount(RegistrationDto registrationDto, String encodedPassword) {

        Account account = accountToRegistrationDtoMapper.toEntity(registrationDto);
        // hash the password
        account.setPassword(encodedPassword);

        // save the account
        account = accountRepository.save(account);

        // create a cart and a wishlist if is a user
        if (!"admin@email.test".equals(account.getEmail())) {
            cartService.createCart(account);
            wishlistService.createWishlist(account);
        } else {
            // populate products if is admin (for testing purpose)
            productService.populateProductsWithDummyData();
        }
        return accountMapper.toDto(account);
    }
}
