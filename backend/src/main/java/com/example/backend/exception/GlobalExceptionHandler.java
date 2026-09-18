package com.example.backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler (UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFound(UserNotFoundException exception){
        return exception.getMessage();
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidation(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        
        exception.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));

        return new ValidationErrorResponse(400, "Validation failed", errors);
    }
}
