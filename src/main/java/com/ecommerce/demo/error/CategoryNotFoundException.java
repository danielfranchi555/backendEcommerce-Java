package com.ecommerce.demo.error;

public class CategoryNotFoundException extends  Exception{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
