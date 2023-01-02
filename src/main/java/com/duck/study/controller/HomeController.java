package com.duck.study.controller;

import com.duck.core.exception.CommonException;
import com.duck.core.security.CustomUserDetailsService;
import com.duck.core.security.JwtTokenProvider;
import com.duck.core.wrapper.ResultResponse;
import com.duck.study.dto.LoginDto;
import com.duck.study.dto.SignupDto;
import com.duck.study.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by eunduck on 2022/11/02.
 */

@Slf4j
@RestController
@Api(tags = "로그인 API")
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @ApiOperation(value = "회원 로그인(인증)")
    @PostMapping("login")
    public ResultResponse<String> token(@RequestBody LoginDto loginDto) {
        log.info("/login 호출");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        UserDetails user = customUserDetailsService.loadUserByUsername(loginDto.getEmail());
        final String token = jwtTokenProvider.generateToken(user);
        return new ResultResponse<>(token);
    }

    @ApiOperation(value = "회원 가입")
    @PostMapping("signup")
    public ResultResponse signup(@Valid @RequestBody SignupDto signupDto) {
        if (userService.checkEmailDuplicate(signupDto.getEmail())) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "이미 등록된 이메일입니다.");
        }
        userService.signup(signupDto);
        return new ResultResponse<>(HttpStatus.OK);
    }
}
