package com.ph.kata_product_trial.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationDto {

    @NotBlank
    private String username;
    private String firstname;
    @Email
    private String email;
    @NotBlank
    private String password;
}
