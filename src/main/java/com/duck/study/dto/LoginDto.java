package com.duck.study.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by eunduck on 2022/10/31.
 */
@Getter
@Setter
@ApiModel(value = "회원 주문 정보")
public class LoginDto {
    @ApiModelProperty(value = "이메일")
    private String email;
    @ApiModelProperty(value = "비밀번호")
    private String password;
}
