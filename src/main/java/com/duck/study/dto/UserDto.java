package com.duck.study.dto;

import com.duck.core.code.Gender;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by eunduck on 2022/10/30.
 */
@Getter
@Setter
@ApiModel(value = "회원 상세 정보 조회")
public class UserDto {

    private Long id;

    private String name;

    private String nickname;

    private String phone;

    private String email;

    private Gender gender;
}
