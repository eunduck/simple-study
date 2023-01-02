package com.duck.study.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by eunduck on 2022/10/30.
 */
@Getter
@Setter
@ApiModel(value = "회원 목록 조회")
public class UserListDto {
    private Long id;

    private String name;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd a HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime lastOrderAt;
}
