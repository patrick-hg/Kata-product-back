package com.ph.kata_product_trial.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationDto {

    private String email;
    private String password;
}
