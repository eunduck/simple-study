package com.duck.core.security;

import com.duck.study.converter.UserConverter;
import com.duck.study.dto.LoginDto;
import com.duck.study.persistence.entity.UserEntity;
import com.duck.study.persistence.repository.CustomerUserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by eunduck on 2022/10/30.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomerUserDetailsRepository repository;
    private final UserConverter converter;

    public UserDetails loadUserByUsername(String email) {
        UserEntity userEntity = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자가 없습니다."));
        LoginDto user = converter.convertSecurity(userEntity);
        return new SecurityUser(user);
    }
}
