package com.example.demo.controller;

import com.example.demo.model.output.CustomerOutput;
import com.example.demo.model.output.OrderOutput;
import com.example.demo.model.output.PizzaOutput;
import com.example.demo.model.output.ToppingOutput;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTests {

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;

    protected static String SERVICE_URL = "http://localhost:";
    protected static String ALL_ORDERS_URL = "/orders";
    protected static String ORDER_UUID = "123e4567-e89b-42d3-a456-556642440000";
    protected static String FIRST_CUSTOMER_UUID = "19e7a509-38a5-401a-8467-6b22ecbe519f";
    protected static String SINGLE_ORDER_URL = "/order";
    protected static String COOKING_STATE = "COOKING";

    protected void verifyFirstOrder(OrderOutput singleOrder) {
        CustomerOutput customer = singleOrder.getCustomerOutput();
        assertEquals(UUID.fromString(FIRST_CUSTOMER_UUID), customer.getUuid());
        assertEquals("Alina", customer.getFirstName());
        assertEquals("Lopez", customer.getLastName());
        assertEquals("WAITING", singleOrder.getOrderStatus());
        assertEquals("ONLINE", singleOrder.getPayOption());
        assertEquals("123e4567-e89b-42d3-a456-556642440000", singleOrder.getUuid());

        List<PizzaOutput> pizzas = singleOrder.getPizzas();
        assertEquals(2, pizzas.size());
        PizzaOutput currentPizza = pizzas.get(0);
        assertEquals("NORMAL", currentPizza.getPizzaType());
        List<ToppingOutput> toppings = currentPizza.getToppings();
        assertEquals(3, toppings.size());
        assertEquals("cheese", toppings.get(0).getName());
        assertEquals("egg", toppings.get(1).getName());
        assertEquals("olives", toppings.get(2).getName());
    }

    protected void verifySecondOrder(OrderOutput orderOutput) {
        PizzaOutput currentPizza;
        List<PizzaOutput> pizzas;
        List<ToppingOutput> toppings;
        CustomerOutput customer;
        customer = orderOutput.getCustomerOutput();
        assertEquals(UUID.fromString("6d506b61-631d-49db-84a1-da1ef497fd1f"), customer.getUuid());
        assertEquals("Marcia", customer.getFirstName());
        assertEquals("Tenemo", customer.getLastName());
        assertEquals("WAITING", orderOutput.getOrderStatus());
        assertEquals("ONLINE", orderOutput.getPayOption());
        assertEquals("eb5dcd25-794d-41bc-8c80-915b339aef08", orderOutput.getUuid());

        pizzas = orderOutput.getPizzas();
        assertEquals(1, pizzas.size());
        currentPizza = pizzas.get(0);
        assertEquals("NORMAL", currentPizza.getPizzaType());
        toppings = currentPizza.getToppings();
        assertEquals(1, toppings.size());
        assertEquals("cheese", toppings.get(0).getName());
    }

    protected void verifyThirdOrder(OrderOutput orderOutput) {
        PizzaOutput currentPizza;
        List<PizzaOutput> pizzas;
        List<ToppingOutput> toppings;
        CustomerOutput customer;
        customer = orderOutput.getCustomerOutput();
        assertEquals(UUID.fromString("1b0aafc3-c89a-4ea3-b79c-f4da18b905e2"), customer.getUuid());
        assertEquals("Albert", customer.getFirstName());
        assertEquals("Rikol", customer.getLastName());
        assertEquals("READY", orderOutput.getOrderStatus());
        assertEquals("OFFLINE", orderOutput.getPayOption());
        assertEquals("0c810aed-0b11-4fee-86c5-c60f0909e9f3", orderOutput.getUuid());

        pizzas = orderOutput.getPizzas();
        assertEquals(1, pizzas.size());
        currentPizza = pizzas.get(0);
        assertEquals("VEGGIE", currentPizza.getPizzaType());
        toppings = currentPizza.getToppings();
        assertEquals(0, toppings.size());
    }

}
