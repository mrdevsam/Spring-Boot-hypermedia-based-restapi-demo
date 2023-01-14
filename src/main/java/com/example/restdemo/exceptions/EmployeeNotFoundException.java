package com.example.restdemo.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Long id) {
        super("employee not found of id: " + id);
    }
}
