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

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPayOption(PayOption payOption) {
        this.payOption = payOption;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}


