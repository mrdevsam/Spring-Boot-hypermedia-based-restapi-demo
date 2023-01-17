package com.example.restdemo.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restdemo.bootstrap.OrderModelAssembler;
import com.example.restdemo.model.OrdeR;
import com.example.restdemo.repositories.OrderRepository;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler orderModelAssembler;

    public OrderController(OrderRepository orderRepository, OrderModelAssembler orderModelAssembler) {
        this.orderRepository = orderRepository;
        this.orderModelAssembler = orderModelAssembler;
    }

    @GetMapping("/orders")
    public CollectionModel<EntityModel<OrdeR>> all() {
        return null;
    }

    @GetMapping("/orders/{id}")
    public EntityModel<OrdeR> one(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/orders")
    public ResponseEntity<EntityModel<OrdeR>> newOrder(@RequestBody OrdeR order) {
        return null;
    }
}
