package com.example.restdemo.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.restdemo.model.Employee;
import com.example.restdemo.model.OrdeR;
import com.example.restdemo.model.Status;
import com.example.restdemo.repositories.EmployeeRepository;
import com.example.restdemo.repositories.OrderRepository;

@Configuration
public class LoadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository, OrderRepository orderRepository) {
        return args -> {

            //employees
            logger.info("Preloading " + repository.save(new Employee("Bilbo", "Baggins", "burglar")));
            logger.info("Preloading " + repository.save(new Employee("Frodo", "Baggins", "thief")));

            repository.findAll().forEach(employee -> logger.info("Preloaded: " + employee));

            //orders
            orderRepository.save(new OrdeR("Mackbook Pro", Status.COMPLETED));
            orderRepository.save(new OrdeR("Iphone 14 Pro", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> logger.info("Preloaded: " + order));
        };
    }
}
