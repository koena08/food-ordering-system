package com.jumpstart.food_ordering_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Custom exceptions provide specific error definitions for the application to handle unique business logic failures
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {

        super(message);
    }
}
