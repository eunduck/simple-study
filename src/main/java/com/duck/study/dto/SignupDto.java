package com.duck.study.dto;

import com.duck.core.code.Gender;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by eunduck on 2022/10/30.
 */
@Getter
@ApiModel(value = "회원가입")
@ToString(of = {"name", "nickname", "phone", "email", "gender", "createdAt"})
public class SignupDto {

    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Pattern(regexp="^[a-zA-Z가-힣]+$", message = "이름은 한글, 영문 대소문자만 허용합니다.")
    @Size(max = 20, message = "이름은 20자 이하 입력 가능합니다.")
    private String name;

    @NotBlank(message = "별명은 필수 입력값입니다.")
    @Pattern(regexp="^[a-z]+$", message = "별명은 영문 소문자만 가능합니다.")
    @Size(max = 30, message = "별명은 30자 이하 입력 가능합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,}",
            message = "비밀번호는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함된 10자리 이상의 비밀번호여야 합니다.")
    private String password;

    @Pattern(regexp="^[0-9]+$", message = "전화번호는 숫자만 허용합니다.")
    @Size(max = 20, message = "전화번호는 최대 20자 이하 입력 가능합니다.")
    private String phone;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @Size(max = 100)
    private String email;

    private Gender gender;
}
