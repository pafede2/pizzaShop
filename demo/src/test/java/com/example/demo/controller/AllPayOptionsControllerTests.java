package com.example.demo.controller;

import com.example.demo.model.PayOption;
import com.example.demo.model.output.CustomerOutput;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AllPayOptionsControllerTests extends OrderControllerTests {

    @Test
    public void getAllThePayOptions() throws Exception {

        // GIVEN 3 customers set on the DB

        // WHEN all the customers are requested
        ResponseEntity<PayOption[]> responseEntity = this.restTemplate.getForEntity( SERVICE_URL + port + ALL_PAY_OPTIONS_URL,
                PayOption[].class);

        // THEN the request executes successfully
        assertEquals(200, responseEntity.getStatusCode().value());

        // AND 3 customers are retrieved
        PayOption[] payOptions = responseEntity.getBody();
        assertEquals(2, payOptions.length);

        // AND the details of first order are correct
        PayOption payOption = payOptions[0];
        assertEquals("ONLINE", payOption.getName());

        payOption = payOptions[1];
        assertEquals("OFFLINE", payOption.getName());

    }

}
