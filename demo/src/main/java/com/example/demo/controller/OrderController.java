package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.*;
import com.example.demo.model.input.OrderInput;
import com.example.demo.model.output.OrderOutput;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController
{

    @Autowired
    PizzaOrderService pizzaOrderService;

    @GetMapping("/orders")
    private List<OrderOutput> getAllOrders() {
        return pizzaOrderService.getAllOrders();
    }

    @PostMapping("/order")
    private OrderOutput newOrder(@RequestBody OrderInput order) {
        return pizzaOrderService.saveOrder(order);
    }

    @PatchMapping("/order/{uuid}")
    private void changeOrderStatus(@PathVariable final String uuid, @RequestBody OrderInput order) {
        pizzaOrderService.updateOrder(UUID.fromString(uuid), order);
    }

    @DeleteMapping("/order/{uuid}")
    private void deleteOrder(@PathVariable final String uuid) {
        pizzaOrderService.delete(UUID.fromString(uuid));
    }

    @GetMapping("/order/{uuid}")
    private OrderOutput changeOrderStatus(@PathVariable final String uuid) {
        return pizzaOrderService.getOrderByUuid(UUID.fromString(uuid));
    }
}