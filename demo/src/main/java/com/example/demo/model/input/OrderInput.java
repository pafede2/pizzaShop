package com.example.demo.model.input;

import com.example.demo.model.Pizza;

import java.util.List;
import java.util.UUID;

public class OrderInput {

    private UUID customerUuid;

    private String payOption;

    private String status;

    private Boolean paymentStatus;

    private List<PizzaInput> pizzas;

    public String getStatus() {
        return status;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public UUID getCustomerUuid() {
        return customerUuid;
    }

    public String getPayOption() {
        return payOption;
    }

    public List<PizzaInput> getPizzas() {
        return pizzas;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setCustomerUuid(UUID customerUuid) {
        this.customerUuid = customerUuid;
    }

    public void setPayOption(String payOption) {
        this.payOption = payOption;
    }

    public void setPizzas(List<PizzaInput> pizzas) {
        this.pizzas = pizzas;
    }
}
