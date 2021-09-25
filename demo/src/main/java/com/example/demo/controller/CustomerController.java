package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.output.CustomerOutput;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    private List<CustomerOutput> getAllCustomers() {
        return customerService.getAllCustomers();
    }

}
