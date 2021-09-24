package com.example.demo.model.output;

import com.example.demo.model.OrderStatus;
import com.example.demo.model.PayOption;
import com.example.demo.model.Pizza;
import com.example.demo.model.Topping;

import java.util.List;
import java.util.UUID;

public class OrderOutput {

    private String uuid;

    private CustomerOutput customerOutput;

    private String payOption;

    private String orderStatus;

    private Boolean isPayed;

    private List<PizzaOutput> pizzas;

    private Double totalPrice;

    private String deliveryAddress;

    private OrderOutput() {
    }

    public static final class OrderOutputBuilder {

        private String uuid;

        private CustomerOutput customerOutput;

        private String payOption;

        private String orderStatus;

        private Boolean isPayed;

        private List<PizzaOutput> pizzas;

        private Double totalPrice;

        private String deliveryAddress;

        public OrderOutputBuilder withUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public OrderOutputBuilder withCustomerOutput(CustomerOutput customerOutput) {
            this.customerOutput = customerOutput;
            return this;
        }

        public OrderOutputBuilder withPayOption(String payOption) {
            this.payOption = payOption;
            return this;
        }

        public OrderOutputBuilder withOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderOutputBuilder withIsPayed(Boolean isPayed) {
            this.isPayed = isPayed;
            return this;
        }

        public OrderOutputBuilder withPizzas(List<PizzaOutput> pizzas) {
            this.pizzas = pizzas;
            return this;
        }

        public OrderOutputBuilder withDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public OrderOutput build() {
            OrderOutput orderOutput = new OrderOutput();
            orderOutput.setUuid(this.uuid);
            orderOutput.setCustomerOutput(this.customerOutput);
            orderOutput.setPayOption(this.payOption);
            orderOutput.setOrderStatus(this.orderStatus);
            orderOutput.setIsPayed(this.isPayed);
            orderOutput.setPizzas(this.pizzas);
            orderOutput.setDeliveryAddress(this.deliveryAddress);
            orderOutput.calculateTotalPrice();
            return orderOutput;
        }

    }

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

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
}
