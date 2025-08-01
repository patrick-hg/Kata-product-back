package com.ph.kata_product_trial.dto.mapper;

import com.ph.kata_product_trial.dto.AccountDto;
import com.ph.kata_product_trial.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements Mapper<Account, AccountDto> {
    @Override
    public Account toEntity(AccountDto dto) {
        return Account.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public AccountDto toDto(Account entity) {
        return AccountDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .email(entity.getEmail())
                .build();
    }
}
