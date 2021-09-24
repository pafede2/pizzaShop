package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.exception.CustomerMalFormedException;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.*;


@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers()
    {
        List<Customer> customers = new ArrayList<Customer>();
        customerRepository.findAll().forEach(customer -> customers.add(customer));
        return customers;
    }

    public Customer getCustomerById(int id)
    {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException();
        }
        return customer.get();
    }
    public void saveOrUpdate(Customer customer)
    {
        if (customer.getFirstName() == null || customer.getLastName() == null || customer.getCardNumber() == null) {
            throw new CustomerMalFormedException();
        }
        customerRepository.save(customer);
    }
    //deleting a specific record
    public void delete(int id)
    {
        customerRepository.deleteById(id);
    }


}
