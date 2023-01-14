package com.example.restdemo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restdemo.bootstrap.EmployeeModelAssembler;
import com.example.restdemo.exceptions.EmployeeNotFoundException;
import com.example.restdemo.model.Employee;
import com.example.restdemo.repositories.EmployeeRepository;

@RestController
public class EmployeeController {

    // dependency injection
    private final EmployeeRepository employeeRepository;
    private final EmployeeModelAssembler employeeModelAssembler;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler employeeModelAssembler) {
        this.employeeRepository = employeeRepository;
        this.employeeModelAssembler = employeeModelAssembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() {
        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
                .map(employeeModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    // single item

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        return employeeModelAssembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return employeeRepository.save(employee);
        }).orElseGet(() -> {
            newEmployee.setId(id);
            return employeeRepository.save(newEmployee);
        });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
