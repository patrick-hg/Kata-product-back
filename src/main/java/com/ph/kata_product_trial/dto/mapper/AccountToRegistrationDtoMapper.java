package com.ph.kata_product_trial.dto.mapper;

import com.ph.kata_product_trial.auth.RegistrationDto;
import com.ph.kata_product_trial.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountToRegistrationDtoMapper implements Mapper<Account, RegistrationDto> {
    @Override
    public Account toEntity(RegistrationDto dto) {
        return Account.builder()
                .email(dto.getEmail())
                .firstname(dto.getFirstname())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    @Override
    public RegistrationDto toDto(Account entity) {
        return RegistrationDto.builder()
                .email(entity.getEmail())
                .firstname(entity.getFirstname())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }
}
