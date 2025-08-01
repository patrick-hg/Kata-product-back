package com.ph.kata_product_trial.rest;

import com.ph.kata_product_trial.auth.AuthenticationDto;
import com.ph.kata_product_trial.auth.RegistrationDto;
import com.ph.kata_product_trial.dto.AccountDto;
import com.ph.kata_product_trial.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/account")
    public ResponseEntity register(@RequestBody RegistrationDto registrationDto) {
        AccountDto accountDto = authenticationService.registerAccount(registrationDto);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping(path = "/token")
    public ResponseEntity authenticate(@RequestBody AuthenticationDto authenticationDto) {
        String token = authenticationService.authenticateAccount(authenticationDto);
        return ResponseEntity.ok(token);
    }
}
