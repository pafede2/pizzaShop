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


public class CreateOrderControllerTests extends OrderControllerTests {

    @Test
    public void createNewOrderSuccessfully() throws Exception {

        // GIVEN 3 orders set on the DB

        // WHEN a new single order is created
        OrderInput order = new OrderInput();
        order.setCustomerUuid(UUID.fromString(FIRST_CUSTOMER_UUID));
        order.setPayOption("ONLINE");
        order.setStatus(COOKING_STATE);
        List<PizzaInput> pizzas = new ArrayList<>();
        pizzas.add(new PizzaInput("NORMAL"));
        pizzas.add(new PizzaInput("VEGGIE"));
        order.setPizzas(pizzas);
        order.setDeliveryAddress("New address");
        HttpEntity<OrderInput> request = new HttpEntity<>(order);
        ResponseEntity<OrderOutput> responseEntity = this.restTemplate.postForEntity(SERVICE_URL + port + SINGLE_ORDER_URL, request, OrderOutput.class);

        // THEN the request executes successfully
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // AND the order returned is the right one
        OrderOutput singleOrder = responseEntity.getBody();
        CustomerOutput customer = singleOrder.getCustomerOutput();
        assertEquals(UUID.fromString(FIRST_CUSTOMER_UUID), customer.getUuid());
        assertEquals("Alina", customer.getFirstName());
        assertEquals("Lopez", customer.getLastName());
        assertEquals(COOKING_STATE, singleOrder.getOrderStatus());
        assertEquals("ONLINE", singleOrder.getPayOption());

        List<PizzaOutput> pizzasResult = singleOrder.getPizzas();
        assertEquals(2, pizzasResult.size());

        PizzaOutput currentPizza = pizzasResult.get(0);
        assertEquals("NORMAL", currentPizza.getPizzaType());
        List<ToppingOutput> toppings = currentPizza.getToppings();
        assertEquals(0, toppings.size());

        currentPizza = pizzasResult.get(1);
        assertEquals("VEGGIE", currentPizza.getPizzaType());
        toppings = currentPizza.getToppings();
        assertEquals(0, toppings.size());

        this.restTemplate.delete(new URI(SERVICE_URL + port + SINGLE_ORDER_URL + "/" + singleOrder.getUuid()));


    }

    @Test
    public void createNewOrderCustomerNotFound() throws Exception {

        // GIVEN 3 orders set on the DB

        // WHEN a new single order is created with unknown customer UUID
        OrderInput order = new OrderInput();
        order.setCustomerUuid(UUID.randomUUID());
        order.setPayOption("ONLINE");
        order.setStatus(COOKING_STATE);
        List<PizzaInput> pizzas = new ArrayList<>();
        pizzas.add(new PizzaInput("NORMAL"));
        pizzas.add(new PizzaInput("VEGGIE"));
        order.setPizzas(pizzas);
        HttpEntity<OrderInput> request = new HttpEntity<>(order);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(SERVICE_URL + port + SINGLE_ORDER_URL, request, String.class);

        // THEN the request returns resource not found
        assertEquals(404, responseEntity.getStatusCode().value());
    }

    @Test
    public void createNewOrderPayOptionUnknown() throws Exception {

        // GIVEN 3 orders set on the DB

        // WHEN a new single order is created with unknown payment option
        OrderInput order = new OrderInput();
        order.setCustomerUuid(UUID.fromString((FIRST_CUSTOMER_UUID)));
        order.setPayOption("ONLINEs");
        order.setStatus(COOKING_STATE);
        List<PizzaInput> pizzas = new ArrayList<>();
        pizzas.add(new PizzaInput("NORMAL"));
        pizzas.add(new PizzaInput("VEGGIE"));
        order.setPizzas(pizzas);
        HttpEntity<OrderInput> request = new HttpEntity<>(order);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(SERVICE_URL + port + SINGLE_ORDER_URL, request, String.class);

        // THEN the request returns resource not found
        assertEquals(400, responseEntity.getStatusCode().value());
    }

    @Test
    public void createNewOrderOrderStatusUnknown() throws Exception {

        // GIVEN 3 orders set on the DB

        // WHEN a new single order is created with unknown order status
        OrderInput order = new OrderInput();
        order.setCustomerUuid(UUID.fromString((FIRST_CUSTOMER_UUID)));
        order.setPayOption("ONLINE");
        order.setStatus("INVALID");
        List<PizzaInput> pizzas = new ArrayList<>();
        pizzas.add(new PizzaInput("NORMAL"));
        pizzas.add(new PizzaInput("VEGGIE"));
        order.setPizzas(pizzas);
        HttpEntity<OrderInput> request = new HttpEntity<>(order);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(SERVICE_URL + port + SINGLE_ORDER_URL, request, String.class);

        // THEN the request returns resource not found
        assertEquals(400, responseEntity.getStatusCode().value());
    }

    @Test
    public void createNewOrderPizzaTypeUnknown() throws Exception {

        // GIVEN 3 orders set on the DB

        // WHEN a new single order is created with unknown order status
        OrderInput order = new OrderInput();
        order.setCustomerUuid(UUID.fromString((FIRST_CUSTOMER_UUID)));
        order.setPayOption("ONLINE");
        order.setStatus("COOKING");
        List<PizzaInput> pizzas = new ArrayList<>();
        pizzas.add(new PizzaInput("NORMALE"));
        pizzas.add(new PizzaInput("VEGGIE"));
        order.setPizzas(pizzas);
        HttpEntity<OrderInput> request = new HttpEntity<>(order);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(SERVICE_URL + port + SINGLE_ORDER_URL, request, String.class);

        // THEN the request returns resource not found
        assertEquals(406, responseEntity.getStatusCode().value());
    }

}
