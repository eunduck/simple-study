package com.duck.study.controller;

import com.duck.core.security.JwtTokenProvider;
import com.duck.core.wrapper.ResultResponse;
import com.duck.study.dto.UserDto;
import com.duck.study.service.LogoutService;
import com.duck.study.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by eunduck on 2022/10/28.
 */
@Slf4j
@Api(tags = "로그아웃 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/logout")
public class LogoutController {
    private final JwtTokenProvider jwtTokenProvider;
    private final LogoutService logoutService;
    private final UserService userService;

    @ApiOperation(value = "회원 로그아웃")
    @GetMapping
    public ResultResponse logout(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = authorization != null && authorization.startsWith("Bearer ") ? authorization.substring(7) : "";
        String username = jwtTokenProvider.getUsernameFromToken(token);

        UserDto detail = userService.findByEmail(username);
        logoutService.logout(detail.getId(), token);

        return new ResultResponse<>(HttpStatus.OK);
    }

}