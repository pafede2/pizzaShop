package com.example.demo.model.input;

import com.example.demo.model.Topping;

import java.util.List;

public class PizzaInput {

    private String pizzaType;

    private List<ToppingInput> toppings;

    public PizzaInput() {
    }

    public PizzaInput(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public List<ToppingInput> getToppings() {
        return toppings;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public void setToppings(List<ToppingInput> toppings) {
        this.toppings = toppings;
    }
}
