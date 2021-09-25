package com.example.demo.controller;

import com.example.demo.model.input.OrderInput;
import com.example.demo.model.input.PizzaInput;
import com.example.demo.model.output.CustomerOutput;
import com.example.demo.model.output.OrderOutput;
import com.example.demo.model.output.PizzaOutput;
import com.example.demo.model.output.ToppingOutput;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UpdateOrderControllerTests extends OrderControllerTests {

    @Test
    public void updateNewOrderSuccessfully() throws Exception {

        // GIVEN a new order
        OrderInput order = new OrderInput();
        order.setCustomerUuid(UUID.fromString(FIRST_CUSTOMER_UUID));
        order.setPayOption("ONLINE");
        order.setStatus(COOKING_STATE);
        List<PizzaInput> pizzas = new ArrayList<>();
        pizzas.add(new PizzaInput("NORMAL"));
        pizzas.add(new PizzaInput("VEGGIE"));
        order.setPizzas(pizzas);
        order.setDeliveryAddress("Address");
        HttpEntity<OrderInput> request = new HttpEntity<>(order);
        ResponseEntity<OrderOutput> responseEntity = this.restTemplate.postForEntity(SERVICE_URL + port + SINGLE_ORDER_URL, request, OrderOutput.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        OrderOutput orderOutput = responseEntity.getBody();
        String orderUuid = orderOutput.getUuid();
        assertEquals(COOKING_STATE, orderOutput.getOrderStatus());
        assertEquals("ONLINE", orderOutput.getPayOption());
        assertEquals(false, orderOutput.getIsPayed());
        assertEquals("Address", orderOutput.getDeliveryAddress());

        // WHEN a new single order is updated
        order = new OrderInput();
        order.setDeliveryAddress("New address");
        order.setStatus(CANCELLED_STATE);
        order.setPaymentStatus(true);

        request = new HttpEntity<>(order);
        orderOutput = restTemplate.postForObject(SERVICE_URL + port + SINGLE_ORDER_URL + "/" + orderUuid + "?_method=patch", request, OrderOutput.class);

        // AND the order has been updated properly
        CustomerOutput customer = orderOutput.getCustomerOutput();
        assertEquals(UUID.fromString(FIRST_CUSTOMER_UUID), customer.getUuid());
        assertEquals("Alina", customer.getFirstName());
        assertEquals("Lopez", customer.getLastName());
        assertEquals(CANCELLED_STATE, orderOutput.getOrderStatus());
        assertEquals("ONLINE", orderOutput.getPayOption());
        assertEquals(true, orderOutput.getIsPayed());
        assertEquals("New address", orderOutput.getDeliveryAddress());

        List<PizzaOutput> pizzasResult = orderOutput.getPizzas();
        assertEquals(2, pizzasResult.size());

        PizzaOutput currentPizza = pizzasResult.get(0);
        assertEquals("NORMAL", currentPizza.getPizzaType());
        List<ToppingOutput> toppings = currentPizza.getToppings();
        assertEquals(0, toppings.size());

        currentPizza = pizzasResult.get(1);
        assertEquals("VEGGIE", currentPizza.getPizzaType());
        toppings = currentPizza.getToppings();
        assertEquals(0, toppings.size());

        this.restTemplate.delete(new URI(SERVICE_URL + port + SINGLE_ORDER_URL + "/" + orderUuid));

    }

    @Test
    public void updateNewOrderAddressError() throws Exception {

        // GIVEN a new order
        OrderInput order = new OrderInput();
        order.setCustomerUuid(UUID.fromString(FIRST_CUSTOMER_UUID));
        order.setPayOption("ONLINE");
        order.setStatus(COOKING_STATE);
        List<PizzaInput> pizzas = new ArrayList<>();
        pizzas.add(new PizzaInput("NORMAL"));
        pizzas.add(new PizzaInput("VEGGIE"));
        order.setPizzas(pizzas);
        order.setDeliveryAddress("Address");
        HttpEntity<OrderInput> request = new HttpEntity<>(order);
        ResponseEntity<OrderOutput> responseEntity = this.restTemplate.postForEntity(SERVICE_URL + port + SINGLE_ORDER_URL, request, OrderOutput.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        OrderOutput orderOutput = responseEntity.getBody();
        String orderUuid = orderOutput.getUuid();

        // WHEN a new single order is updated
        order = new OrderInput();
        order.setStatus(CANCELLED_STATE);
        order.setPaymentStatus(true);

        request = new HttpEntity<>(order);
        responseEntity = restTemplate.postForEntity(SERVICE_URL + port + SINGLE_ORDER_URL + "/" + orderUuid + "?_method=patch", request, OrderOutput.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

}
