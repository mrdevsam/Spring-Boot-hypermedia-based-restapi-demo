package com.example.restdemo.bootstrap;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.restdemo.controllers.EmployeeController;
import com.example.restdemo.model.Employee;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> toModel(Employee entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(EmployeeController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }

}
