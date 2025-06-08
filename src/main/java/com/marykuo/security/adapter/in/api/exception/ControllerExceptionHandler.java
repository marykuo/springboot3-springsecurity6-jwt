package com.marykuo.security.adapter.in.api.exception;

import com.marykuo.security.adapter.in.api.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<BaseResponse> handler(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new BaseResponse(e.getMessage()));
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Void> handler(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
