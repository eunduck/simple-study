package com.duck.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Created by eunduck on 2022/10/30.
 */
@Getter
@Setter
public class NoDataFoundException extends RuntimeException {
    private HttpStatus httpStatus;

    public NoDataFoundException(HttpStatus status, String message) {
        super(message);
        this.httpStatus = status;
    }

    public NoDataFoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
