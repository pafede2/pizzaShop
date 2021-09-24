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

    //public int getId() {
    //    return id;
    //}

    public PizzaType getPizzaType() {
        return pizzaType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    //public void setId(int id) {
    //    this.id = id;
    //}

    public void setPizzaType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }
}


