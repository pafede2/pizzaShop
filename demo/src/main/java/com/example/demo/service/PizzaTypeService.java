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

    public List<PizzaType> getAllPizzaTypes()
    {
        List<PizzaType> pizzaTypes = new ArrayList<PizzaType>();
        pizzaTypeRepository.findAll().forEach(pizzaType -> pizzaTypes.add(pizzaType));
        return pizzaTypes;
    }


}
