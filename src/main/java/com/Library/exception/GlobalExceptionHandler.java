package com.Library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(value = {NotFoundException.class})
public ResponseEntity<ErrorResponse> handleBookNotFound(NotFoundException bookNotFoundException){
    ErrorResponse errorResponse = new ErrorResponse(bookNotFoundException.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
    return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
}
}
