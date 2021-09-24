package com.example.demo.controller;

import com.example.demo.model.output.CustomerOutput;
import com.example.demo.model.output.OrderOutput;
import com.example.demo.model.output.PizzaOutput;
import com.example.demo.model.output.ToppingOutput;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AllCustomerControllerTests extends OrderControllerTests {

    @Test
    public void getAllTheCustomers() throws Exception {

        // GIVEN 3 customers set on the DB

        // WHEN all the customers are requested
        ResponseEntity<CustomerOutput[]> responseEntity = this.restTemplate.getForEntity( SERVICE_URL + port + ALL_CUSTOMERS_URL,
                CustomerOutput[].class);

        // THEN the request executes successfully
        assertEquals(200, responseEntity.getStatusCode().value());

        // AND 3 customers are retrieved
        CustomerOutput[] allCustomers = responseEntity.getBody();
        assertEquals(3, allCustomers.length);

        // AND the details of first order are correct
        CustomerOutput customer = allCustomers[0];
        assertEquals(UUID.fromString(FIRST_CUSTOMER_UUID), customer.getUuid());
        assertEquals("Alina", customer.getFirstName());
        assertEquals("Lopez", customer.getLastName());

        customer = allCustomers[1];
        assertEquals(UUID.fromString("6d506b61-631d-49db-84a1-da1ef497fd1f"), customer.getUuid());
        assertEquals("Marcia", customer.getFirstName());
        assertEquals("Tenemo", customer.getLastName());

        customer = allCustomers[2];
        assertEquals(UUID.fromString("1b0aafc3-c89a-4ea3-b79c-f4da18b905e2"), customer.getUuid());
        assertEquals("Albert", customer.getFirstName());
        assertEquals("Rikol", customer.getLastName());

    }

}
