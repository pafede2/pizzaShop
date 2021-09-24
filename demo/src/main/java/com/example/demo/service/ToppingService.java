package com.example.demo.service;

import com.example.demo.model.Topping;
import com.example.demo.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ToppingService {

    @Autowired
    ToppingRepository toppingRepository;

    public List<Topping> getAllToppings()
    {
        List<Topping> toppings = new ArrayList<Topping>();
        toppingRepository.findAll().forEach(topping -> toppings.add(topping));
        return toppings;
    }


}
