package com.example.restdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restdemo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
