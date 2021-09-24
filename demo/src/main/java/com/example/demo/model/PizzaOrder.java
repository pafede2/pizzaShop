package com.example.demo.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="pizza_order")
public class PizzaOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    @Type(type="uuid-char")
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "pay_option_id")
    private PayOption payOption;

    @OneToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @Column
    private Boolean paymentStatus;

    @Column
    private String deliveryAddress;


    @JoinTable(
            name = "order_to_pizza",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "pizza_id",
                    referencedColumnName = "id"
            )
    )
    @OneToMany(cascade = CascadeType.ALL)
    private List<Pizza> pizzas;

    public static final class PizzaOrderBuilder {

        private Customer customer;

        private PayOption payOption;

        private OrderStatus orderStatus;

        private String deliveryAddress;

        private List<Pizza> pizzas;

        public PizzaOrderBuilder withCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public PizzaOrderBuilder withPayOption(PayOption payOption) {
            this.payOption = payOption;
            return this;
        }

        public PizzaOrderBuilder withOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public PizzaOrderBuilder withDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public PizzaOrderBuilder withPizzas(List<Pizza> pizzas) {
            this.pizzas = pizzas;
            return this;
        }

        public PizzaOrder build() {
            PizzaOrder pizzaOrder = new PizzaOrder();
            pizzaOrder.setPaymentStatus(Boolean.FALSE);
            pizzaOrder.setCustomer(this.customer);
            pizzaOrder.setPayOption(this.payOption);
            pizzaOrder.setOrderStatus(this.orderStatus);
            pizzaOrder.setPizzas(this.pizzas);
            pizzaOrder.setUuid(UUID.randomUUID());
            pizzaOrder.setDeliveryAddress(this.deliveryAddress);

            return pizzaOrder;
        }
    }

    public int getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public PayOption getPayOption() {
        return payOption;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    private void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private void setPayOption(PayOption payOption) {
        this.payOption = payOption;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    private void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}


