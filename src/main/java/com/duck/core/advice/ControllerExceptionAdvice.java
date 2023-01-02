package com.duck.core.advice;

import com.duck.core.exception.CommonException;
import com.duck.core.exception.NoDataFoundException;
import com.duck.core.wrapper.ResultResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by eunduck on 2022/10/30.
 */
@EnableWebMvc
@RestControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ControllerExceptionAdvice {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ResultResponse> handleException(CommonException e) {
        ResultResponse resultResponse = new ResultResponse(e.getHttpStatus());
        resultResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(resultResponse, e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultResponse> handleException(MethodArgumentNotValidException e) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.BAD_REQUEST);
        resultResponse.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(resultResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultResponse handleException(Exception e) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        resultResponse.setMessage(e.getMessage());
        return resultResponse;
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultResponse handleAuthenticationException(AuthenticationException e) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.FORBIDDEN);
        resultResponse.setMessage("로그인 정보를 확인해주세요.");
        return resultResponse;
    }

    @ExceptionHandler({NoDataFoundException.class})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResultResponse NoDataFoundException(NoDataFoundException e) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.NO_CONTENT);
        resultResponse.setMessage(e.getMessage());
        return resultResponse;
    }

}
