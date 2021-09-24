package com.example.demo.model.output;

import java.util.List;

public class PizzaOutput {

    private String pizzaType;

    private Double price;

    private List<ToppingOutput> toppings;

    public String getPizzaType() {
        return pizzaType;
    }

    public List<ToppingOutput> getToppings() {
        return toppings;
    }

    public Double getPrice() {
        return price;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public void setToppings(List<ToppingOutput> toppings) {
        this.toppings = toppings;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
