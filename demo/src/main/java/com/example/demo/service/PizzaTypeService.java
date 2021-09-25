package com.example.demo.service;

import com.example.demo.model.PizzaType;
import com.example.demo.repository.PizzaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PizzaTypeService {

    @Autowired
    PizzaTypeRepository pizzaTypeRepository;

    public List<String> getAllPizzaTypes()
    {
        List<String> pizzaTypes = new ArrayList<String>();
        pizzaTypeRepository.findAll().forEach(pizzaType -> pizzaTypes.add(pizzaType.getName()));
        return pizzaTypes;
    }


}
