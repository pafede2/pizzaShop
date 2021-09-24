package com.example.demo.repository;

import com.example.demo.model.PizzaOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PizzaOrderRepository extends CrudRepository<PizzaOrder, Integer> {

    PizzaOrder findByUuid(UUID uuid);
}