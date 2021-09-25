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


public class AllToppingControllerTests extends OrderControllerTests {

    @Test
    public void getAllTheOrders() throws Exception {

        // GIVEN 3 toppings/additional ingredients set on the DB

        // WHEN all the toppings are requested
        ResponseEntity<String[]> responseEntity = this.restTemplate.getForEntity( SERVICE_URL + port + ALL_PIZZA_TOPPINGS_URL,
                String[].class);

        // THEN the request executes successfully
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // AND 3 orders are retrieved
        String[] allToppings = responseEntity.getBody();
        assertEquals(3, allToppings.length);

        // AND the details of first order are correct
        assertEquals("cheese", allToppings[0]);
        assertEquals("egg", allToppings[1]);
        assertEquals("olives", allToppings[2]);

    }

}
