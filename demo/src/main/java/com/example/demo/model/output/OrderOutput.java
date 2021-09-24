package com.example.demo.model.output;

import java.util.List;

public class OrderOutput {

    private String uuid;

    private CustomerOutput customerOutput;

    private String payOption;

    private String orderStatus;

    private Boolean isPayed;

    private List<PizzaOutput> pizzas;

    private Double totalPrice;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setCustomerOutput(CustomerOutput customerOutput) {
        this.customerOutput = customerOutput;
    }

    public void setPayOption(String payOption) {
        this.payOption = payOption;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setIsPayed(Boolean isPayed) {
        this.isPayed = isPayed;
    }

    public void setPizzas(List<PizzaOutput> pizzas) {
        this.pizzas = pizzas;
    }

    public void calculateTotalPrice() {
        Double totalPrice = 0.0d;
        for (PizzaOutput pizza : this.getPizzas()) {
            totalPrice += pizza.getPrice();
            for (ToppingOutput topping : pizza.getToppings()) {
                totalPrice += topping.getPrice();
            }
        }
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public CustomerOutput getCustomerOutput() {
        return customerOutput;
    }

    public String getPayOption() {
        return payOption;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Boolean getIsPayed() {
        return isPayed;
    }

    public List<PizzaOutput> getPizzas() {
        return pizzas;
    }

    public String getUuid() {
        return uuid;
    }
}
