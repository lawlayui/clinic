package com.lawlayui.clinic.account.infrastructure.web;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lawlayui.clinic.account.application.exception.UserAlredyExistsException;
import com.lawlayui.clinic.account.application.exception.UserNotFoundException;
import com.lawlayui.clinic.account.domain.exception.InvalidEmailException;
import com.lawlayui.clinic.account.domain.exception.InvalidNameException;
import com.lawlayui.clinic.account.domain.exception.InvalidPhoneNumberException;
import com.lawlayui.clinic.account.infrastructure.web.requestDto.ErrorResponse;

@RestControllerAdvice(basePackages = "com.lawlayui.clinic.account")
public class AccountGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        for (ObjectError globalError : ex.getBindingResult().getGlobalErrors()) {
            errors.put(globalError.getObjectName(), globalError.getDefaultMessage());
        }
        return new ErrorResponse("VALIDATION_ERROR", "Validation failed", errors);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBind(BindException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
          .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ErrorResponse("VALIDATION_ERROR", "Validation failed", errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolation(ConstraintViolationException ex) { 
        Map<String, String> errors = new LinkedHashMap<>();
    
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String field = violation.getPropertyPath().toString();
        
            int lastDotIndex = field.lastIndexOf('.');
            if (lastDotIndex >= 0) {
                field = field.substring(lastDotIndex + 1);
            }
        
            errors.put(field, violation.getMessage());
        }
    
        return new ErrorResponse("VALIDATION_ERROR", "Validation failed", errors);
    }

    @ExceptionHandler(UserAlredyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserAlredyExists(UserAlredyExistsException ex) {
        return new ErrorResponse("USER_ALREDY_EXISTS", ex.getMessage(), null);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorResponse("NOT_FOUND", ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidEmailException(InvalidEmailException ex) {
        return new ErrorResponse("VALIDATION_ERROR", ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidNameException(InvalidNameException ex) {
        return new ErrorResponse("VALIDATION_ERROR", ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidPhoneNumberException(InvalidPhoneNumberException ex) {
        return new ErrorResponse("VALIDATION_ERROR", ex.getMessage(), null);
    }
}