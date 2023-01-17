package com.example.restdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restdemo.model.OrdeR;

public interface OrderRepository extends JpaRepository<OrdeR, Long>{
    
}
