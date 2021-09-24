package com.example.demo.model.output;

import java.util.List;

public class PizzaOutput {

    private String pizzaType;

    private Double price;

    private List<ToppingOutput> toppings;

    private PizzaOutput() {
    }

    public static final class PizzaOutputBuilder {

        private String pizzaType;

        private Double price;

        private List<ToppingOutput> toppings;

        public PizzaOutputBuilder withPizzaType(String pizzaType) {
            this.pizzaType = pizzaType;
            return this;
        }

        public PizzaOutputBuilder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public PizzaOutputBuilder withToppings(List<ToppingOutput> toppings) {
            this.toppings = toppings;
            return this;
        }

        public PizzaOutput build() {
            PizzaOutput pizzaOutput = new PizzaOutput();
            pizzaOutput.setPizzaType(this.pizzaType);
            pizzaOutput.setPrice(this.price);
            pizzaOutput.setToppings(this.toppings);
            return pizzaOutput;
        }

    }


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
