package com.example.demo.repository;

import com.example.demo.model.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Integer>
{
}