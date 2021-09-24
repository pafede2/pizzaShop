package com.example.demo.service;

import com.example.demo.model.OrderStatus;
import com.example.demo.model.PayOption;
import com.example.demo.repository.OrderStatusRepository;
import com.example.demo.repository.PayOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderStatusService {

    @Autowired
    OrderStatusRepository orderStatusRepository;

    public List<OrderStatus> getAllOrderStatuses()
    {
        List<OrderStatus> orderStatuses = new ArrayList<OrderStatus>();
        orderStatusRepository.findAll().forEach(status -> orderStatuses.add(status));
        return orderStatuses;
    }


}
