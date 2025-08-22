package com.example.czelaya.api_rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(ResourceNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                "Resource not found");
        return  new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handlerBadRequestException(BadRequestException e){
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad request");
        return  new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        String errorMessage = "Error validating the fields: " + String.join(", ", errors.values());
        ErrorResponse errorResponse = new ErrorResponse(
                errorMessage,
                HttpStatus.BAD_REQUEST.value(),
                "Validation error");
        return  new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
