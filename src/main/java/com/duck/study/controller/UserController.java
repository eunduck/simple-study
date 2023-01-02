package com.duck.study.controller;

import com.duck.core.wrapper.PageContents;
import com.duck.core.wrapper.ResultResponse;
import com.duck.study.dto.PageRequest;
import com.duck.study.dto.UserDto;
import com.duck.study.dto.UserListDto;
import com.duck.study.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by eunduck on 2022/10/28.
 */
@RestController
@Api(tags = "회원 정보 API")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @ApiOperation(value = "단일 회원 상세 정보 조회")
    @GetMapping("{id}")
    public ResultResponse<UserDto> getUser(@PathVariable Long id) {
        UserDto userDto = service.findById(id);
        return new ResultResponse<>(userDto);
    }

    @ApiOperation(value = "여러 회원 목록 조회")
    @GetMapping
    public ResultResponse<PageContents<UserListDto>> getUsers(PageRequest condition) {
        PageContents<UserListDto> userList = service.findByPaging(condition);
        return new ResultResponse<>(userList);
    }
}