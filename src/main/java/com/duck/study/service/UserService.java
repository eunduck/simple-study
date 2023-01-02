package com.duck.study.service;

import com.duck.core.exception.NoDataFoundException;
import com.duck.core.wrapper.PageContents;
import com.duck.study.converter.UserConverter;
import com.duck.study.dto.PageRequest;
import com.duck.study.dto.SignupDto;
import com.duck.study.dto.UserDto;
import com.duck.study.dto.UserListDto;
import com.duck.study.persistence.entity.UserEntity;
import com.duck.study.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by eunduck on 2022/10/30.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserConverter converter;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public boolean checkEmailDuplicate(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public UserDto findByEmail(String email) {
        UserEntity userEntity = repository.findByEmail(email).orElseThrow(() -> new NoDataFoundException("사용자를 찾을 수 없습니다."));
        return converter.convert(userEntity);
    }

    public void signup(SignupDto signupDto) {
        UserEntity entity = converter.convert(signupDto);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        repository.save(entity);
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        UserEntity userEntity = repository.findById(id).orElseThrow(() -> new NoDataFoundException("사용자를 찾을 수 없습니다."));
        return converter.convert(userEntity);
    }

    public PageContents<UserListDto> findByPaging(PageRequest condition) {
        return repository.searchUserInfos(condition);
    }
}
