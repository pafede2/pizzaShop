package com.example.demo.controller;

import com.example.demo.model.Topping;
import com.example.demo.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToppingsController {

    @Autowired
    ToppingService toppingService;

    @GetMapping("/toppings")
    private List<String> getAllToppings() {
        return toppingService.getAllToppings();
    }


}
