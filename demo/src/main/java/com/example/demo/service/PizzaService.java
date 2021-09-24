package com.example.demo.service;

import com.example.demo.model.Pizza;
import com.example.demo.model.PizzaType;
import com.example.demo.repository.PizzaRepository;
import com.example.demo.repository.PizzaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    public List<Pizza> getAllPizzas()
    {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        pizzaRepository.findAll().forEach(pizza -> pizzas.add(pizza));
        return pizzas;
    }


}
