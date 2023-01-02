package com.duck.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by eunduck on 2022/10/28.
 */

@AllArgsConstructor
@Getter
public enum Gender {
    M("남성"),
    F("여성");

    private final String description;
}
