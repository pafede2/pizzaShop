package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.output.CustomerOutput;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerOutput> getAllCustomers()
    {
        List<CustomerOutput> customers = new ArrayList<CustomerOutput>();
        customerRepository.findAll().forEach(customer ->
                customers.add(new CustomerOutput.CustomerOutputBuilder()
                        .withUuid(customer.getUuid())
                        .withFirstName(customer.getFirstName())
                        .withLastName(customer.getLastName())
                        .build()));
        return customers;
    }

}
