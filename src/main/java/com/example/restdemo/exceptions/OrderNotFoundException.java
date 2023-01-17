package com.example.restdemo.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id) {
        System.out.println("order not found for id: " + id);
    }
}
