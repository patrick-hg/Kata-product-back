package com.ph.kata_product_trial.service;

import com.ph.kata_product_trial.auth.AuthenticationDto;
import com.ph.kata_product_trial.auth.JwtTokenUtils;
import com.ph.kata_product_trial.auth.RegistrationDto;
import com.ph.kata_product_trial.dto.AccountDto;
import com.ph.kata_product_trial.exception.AuthenticationFailedException;
import com.ph.kata_product_trial.exception.FunctionalException;
import com.ph.kata_product_trial.model.Account;
import com.ph.kata_product_trial.repository.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {

    private AccountService accountService;
    private JwtTokenUtils jwtTokenUtils;
    private PasswordEncoder passwordEncoder;
    private AccountRepository accountRepository;

    public AuthenticationService(AccountService accountService,
                                 JwtTokenUtils jwtTokenUtils,
                                 PasswordEncoder passwordEncoder,
                                 AccountRepository accountRepository) {
        this.accountService = accountService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountDto registerAccount(@Valid RegistrationDto registrationDto) {
        // validation

        // check for an existent account
        if (accountService.findAccountByEmail(registrationDto.getEmail()).isPresent()) {
            throw new FunctionalException("Account with specified email already exist, please sign-in");
        }

        // hash password
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

        // create account instance
        return accountService.createAccount(registrationDto, encodedPassword);
    }

    public String authenticateAccount(@Valid AuthenticationDto authenticationDto) {

        Account account = accountRepository.findByEmail(authenticationDto.getEmail())
                .orElseThrow(AuthenticationFailedException::new);

        // check password
        final String encodedPassword = passwordEncoder.encode(authenticationDto.getPassword());
        if (!passwordEncoder.matches(authenticationDto.getPassword(), account.getPassword())) {
            throw new AuthenticationFailedException();
        }

        // generate token
        return jwtTokenUtils.generateToken(authenticationDto.getEmail());
    }
}
