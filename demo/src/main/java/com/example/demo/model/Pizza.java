package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "pizza_type_id")
    private PizzaType pizzaType;

    @JoinTable(
            name = "pizza_to_topping",
            joinColumns = @JoinColumn(
                    name = "pizza_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "topping_id",
                    referencedColumnName = "id"
            )
    )
    @OneToMany
    private List<Topping> toppings;

    private Pizza() {
    }

    public static final class PizzaBuilder {

        private PizzaType pizzaType;

        private List<Topping> toppings;

        public PizzaBuilder withPizzaType(PizzaType pizzaType) {
            this.pizzaType = pizzaType;
            return this;
        }

        public PizzaBuilder withTopping(List<Topping> toppings) {
            this.toppings = toppings;
            return this;
        }

        public Pizza build() {
            Pizza pizza = new Pizza();
            pizza.setPizzaType(this.pizzaType);
            pizza.setToppings(this.toppings);
            return pizza;
        }

    }

    public PizzaType getPizzaType() {
        return pizzaType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    private void setPizzaType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    private void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }
}



