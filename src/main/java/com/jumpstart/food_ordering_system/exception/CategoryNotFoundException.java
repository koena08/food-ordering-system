package com.jumpstart.food_ordering_system.exception;

//Custom exceptions provide specific error definitions for the application to handle unique business logic failures
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        
        super(message);
    }
}
