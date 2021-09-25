package com.example.demo.controller;

import com.example.demo.model.PizzaType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AllPizzaTypesControllerTests extends OrderControllerTests {

    @Test
    public void getAllThePizzaTypes() throws Exception {

        // GIVEN 3 customers set on the DB

        // WHEN all the customers are requested
        ResponseEntity<String[]> responseEntity = this.restTemplate.getForEntity( SERVICE_URL + port + ALL_PIZZA_TYPES_URL,
                String[].class);

        // THEN the request executes successfully
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // AND 3 customers are retrieved
        String[] pizzaType = responseEntity.getBody();
        assertEquals(2, pizzaType.length);

        // AND the details of first order are correct
        String payOption = pizzaType[0];
        assertEquals("NORMAL", payOption);

        payOption = pizzaType[1];
        assertEquals("VEGGIE", payOption);

    }

}
