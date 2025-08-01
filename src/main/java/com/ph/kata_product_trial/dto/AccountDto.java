package com.ph.kata_product_trial.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {
    private String id;
    private String username;
    private String firstname;
    private String email;
}
