package com.example.restdemo.bootstrap;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.restdemo.controllers.OrderController;
import com.example.restdemo.model.OrdeR;
import com.example.restdemo.model.Status;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<OrdeR, EntityModel<OrdeR>> {

    @Override
    public EntityModel<OrdeR> toModel(OrdeR entity) {

        // unconditional links to single-item resource and aggregate-root
        EntityModel<OrdeR> orderModel = EntityModel.of(entity,
                linkTo(methodOn(OrderController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("orders"));

        // conditional links based on state of the order
        if (entity.getStatus() == Status.IN_PROGRESS) {
            orderModel.add(linkTo(methodOn(OrderController.class).cancel(entity.getId())).withRel("cancel"));

            orderModel.add(linkTo(methodOn(OrderController.class).complete(entity.getId())).withRel("complete"));
        }
        return orderModel;
    }

}
