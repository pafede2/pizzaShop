package com.example.demo.controller;

import com.example.demo.model.PizzaType;
import com.example.demo.service.PizzaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PizzaTypesController {

    @Autowired
    PizzaTypeService pizzaTypeService;

    @GetMapping("/pizza_types")
    private List<String> getAllPizzaTypes() {
        return pizzaTypeService.getAllPizzaTypes();
    }

}
