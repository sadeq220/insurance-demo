package com.lookinsure.insuranceDemo.application.rest;

import com.lookinsure.insuranceDemo.application.rest.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestfulExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleEndpointArgumentNotValid(MethodArgumentNotValidException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getBody().getDetail());
        errorDto.setCode(exception.getTitleMessageCode());
        return ResponseEntity.badRequest().body(errorDto);
    }
}
