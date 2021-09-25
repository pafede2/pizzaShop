package com.example.demo.controller;

import com.example.demo.model.output.CustomerOutput;
import com.example.demo.model.output.OrderOutput;
import com.example.demo.model.output.PizzaOutput;
import com.example.demo.model.output.ToppingOutput;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AllOrderControllerTests extends OrderControllerTests {

    @Test
    public void getAllTheOrders() throws Exception {

        // GIVEN 3 orders set on the DB

        // WHEN all the orders are requested
        ResponseEntity<OrderOutput[]> responseEntity = this.restTemplate.getForEntity( SERVICE_URL + port + ALL_ORDERS_URL,
                OrderOutput[].class);

        // THEN the request executes successfully
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // AND 3 orders are retrieved
        OrderOutput[] allOrders = responseEntity.getBody();
        assertEquals(3, allOrders.length);

        // AND the details of first order are correct
        OrderOutput orderOutput = allOrders[0];
        verifyFirstOrder(orderOutput);
        CustomerOutput customer;
        List<PizzaOutput> pizzas;
        PizzaOutput currentPizza;
        List<ToppingOutput> toppings;

        // AND the details of second order are correct
        orderOutput = allOrders[1];
        verifySecondOrder(orderOutput);

        // AND the details of third order are correct
        orderOutput = allOrders[2];
        verifyThirdOrder(orderOutput);

    }

}
