package com.duck.core.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Created by eunduck on 2022/10/30.
 */

@Data
@NoArgsConstructor
public class ResultResponse<T> {
    private int status;
    private String message;
    private T result;

    public ResultResponse(T result) {
        this.status = HttpStatus.OK.value();
        this.message = "success";
        this.result = result;
    }

    public ResultResponse(HttpStatus httpStatus) {
        this.status = httpStatus.value();
    }
}