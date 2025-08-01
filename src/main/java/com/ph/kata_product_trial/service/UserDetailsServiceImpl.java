package com.ph.kata_product_trial.service;

import com.ph.kata_product_trial.model.Account;
import com.ph.kata_product_trial.repository.AccountRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email).orElseThrow();
        return User.builder()
                .username(email)
                .password(account.getPassword())
                .authorities("USER")
                .build();
    }

    public UserDetails getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (Strings.isBlank(email)) {
            return null;
        }
        return loadUserByUsername(email);
    }
}
