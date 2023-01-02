package com.duck.study.converter;

import com.duck.study.dto.LoginDto;
import com.duck.study.dto.SignupDto;
import com.duck.study.dto.UserDto;
import com.duck.study.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * Created by eunduck on 2022/10/30.
 */
@Mapper(componentModel = "spring")
public interface UserConverter {
    UserEntity convert(SignupDto signupDto);

    LoginDto convertSecurity(UserEntity entity);

    UserDto convert(UserEntity entity);

}
