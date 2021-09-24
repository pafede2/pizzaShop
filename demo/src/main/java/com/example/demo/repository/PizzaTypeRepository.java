package com.example.demo.repository;

import com.example.demo.model.PizzaType;
import org.springframework.data.repository.CrudRepository;

public interface PizzaTypeRepository extends CrudRepository<PizzaType, Integer>
{
}