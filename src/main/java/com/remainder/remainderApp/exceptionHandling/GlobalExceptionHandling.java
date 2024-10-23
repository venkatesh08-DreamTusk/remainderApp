package com.remainder.remainderApp.exceptionHandling;

import com.remainder.remainderApp.apiResponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> globalException(Exception e){
        return ApiResponse.<Object>builder()
                .status(false)
                .data(null)
                .error(e.getMessage())
                .build();
    }
}
