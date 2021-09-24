package com.example.demo.repository;

import com.example.demo.model.Topping;
import org.springframework.data.repository.CrudRepository;

public interface ToppingRepository extends CrudRepository<Topping, Integer>
{
}