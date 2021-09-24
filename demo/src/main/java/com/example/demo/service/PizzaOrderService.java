package com.example.demo.service;

import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.model.input.OrderInput;
import com.example.demo.model.input.PizzaInput;
import com.example.demo.model.input.ToppingInput;
import com.example.demo.model.output.CustomerOutput;
import com.example.demo.model.output.OrderOutput;
import com.example.demo.model.output.PizzaOutput;
import com.example.demo.model.output.ToppingOutput;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class PizzaOrderService {

    @Autowired
    PizzaOrderRepository pizzaOrderRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PayOptionRepository payOptionRepository;

    @Autowired
    PizzaTypeRepository pizzaTypeRepository;

    @Autowired
    ToppingRepository toppingRepository;

    public List<OrderOutput> getAllOrders()
    {
        List<OrderOutput> orders = new ArrayList<OrderOutput>();
        pizzaOrderRepository.findAll().forEach(order -> {
            orders.add(orderFromDbToOutput(order));
        });
        return orders;
    }

    private OrderOutput orderFromDbToOutput(PizzaOrder order) {
        OrderOutput orderOutput = new OrderOutput();
        orderOutput.setUuid(order.getUuid().toString());
        orderOutput.setIsPayed(order.getPaymentStatus());
        orderOutput.setOrderStatus(order.getOrderStatus().getName());
        orderOutput.setPayOption(order.getPayOption().getName());
        orderOutput.setPizzas(order.getPizzas().stream().map(pizza -> {
            PizzaOutput pizzaOutput = new PizzaOutput();
            pizzaOutput.setPizzaType(pizza.getPizzaType().getName());
            pizzaOutput.setPrice(pizza.getPizzaType().getPrice());
            pizzaOutput.setToppings(pizza.getToppings().stream().map(topping -> {
                ToppingOutput toppingOutput = new ToppingOutput();
                toppingOutput.setName(topping.getName());
                toppingOutput.setPrice(topping.getPrice());
                return toppingOutput;
            }).collect(Collectors.toList()));
            return pizzaOutput;
        }).collect(Collectors.toList()));
        orderOutput.setCustomerOutput(new CustomerOutput(order.getCustomer().getUuid(), order.getCustomer().getFirstName(), order.getCustomer().getLastName()));
        orderOutput.calculateTotalPrice();
        return orderOutput;
    }

    public OrderOutput saveOrder(OrderInput order) throws PizzaTypeNotFoundException, ToppingNotFoundException, CustomerNotFoundException, PayOptionNotFoundException, OrderStatusNotFoundException {

        Customer customer = customerRepository.findByUuid(order.getCustomerUuid());
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        PayOption payOption = payOptionRepository.findByName(order.getPayOption());
        if (payOption == null) {
            throw new PayOptionNotFoundException();
        }

        OrderStatus orderStatus = orderStatusRepository.findByName(order.getStatus());
        if (orderStatus == null) {
            throw new OrderStatusNotFoundException();
        }

        List<PizzaInput> pizzasInOrder = order.getPizzas();
        List<Pizza> pizzas = new ArrayList<>();

        Map<String, PizzaType> pizzaTypes = new TreeMap<>();
        pizzaTypeRepository.findAll().forEach(pizzaType -> {
            pizzaTypes.put(pizzaType.getName(), pizzaType);
        });

        boolean allPizzaTypesExists = pizzasInOrder.stream().allMatch(x -> pizzaTypes.getOrDefault(x.getPizzaType(), null) != null);
        if (!allPizzaTypesExists) {
            throw new PizzaTypeNotFoundException();
        }

        Map<String, Topping> allPizzaToppings = new TreeMap<>();
        toppingRepository.findAll().forEach(topping -> {
            allPizzaToppings.put(topping.getName(), topping);
        });

        pizzasInOrder.forEach(pizzaInOrder -> {
            Pizza pizza = new Pizza();
            pizza.setPizzaType(pizzaTypes.get(pizzaInOrder.getPizzaType()));
            List<Topping> toppingsForPizza = new ArrayList<>();

            List<ToppingInput> toppings = pizzaInOrder.getToppings();
            if (toppings != null) {
                toppings.forEach(toppingInPizza -> {
                    Topping currentTopping = allPizzaToppings.getOrDefault(toppingInPizza.getToppingName(), null);
                    if (currentTopping == null) {
                        throw new ToppingNotFoundException();
                    } else {
                        toppingsForPizza.add(currentTopping);
                    }
                });
            }
            pizza.setToppings(toppingsForPizza);
            pizzas.add(pizza);
        });

        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setPaymentStatus(Boolean.FALSE);
        pizzaOrder.setCustomer(customer);
        pizzaOrder.setPayOption(payOption);
        pizzaOrder.setOrderStatus(orderStatus);
        pizzaOrder.setPizzas(pizzas);
        pizzaOrder.setUuid(UUID.randomUUID());

        PizzaOrder pizza = pizzaOrderRepository.save(pizzaOrder);
        return orderFromDbToOutput(pizza);

    }

    public OrderOutput getOrderByUuid(UUID orderUuid) throws OrderStatusNotFoundException {
        PizzaOrder order = pizzaOrderRepository.findByUuid(orderUuid);

        if (order == null) {
            throw new PizzaOrderNotFoundException();
        }

        return orderFromDbToOutput(order);
    }

    public void updateOrder(UUID orderUuid, OrderInput order) throws OrderStatusNotFoundException {
        OrderStatus orderStatus = orderStatusRepository.findByName(order.getStatus());

        if (order.getStatus() != null && orderStatus == null) {
            throw new OrderStatusNotFoundException();
        }

        PizzaOrder pizzaOrder = pizzaOrderRepository.findByUuid(orderUuid);

        if (pizzaOrder == null) {
            throw new PizzaOrderNotFoundException();
        }

        if (order.getPaymentStatus() != null) {
            pizzaOrder.setPaymentStatus(order.getPaymentStatus());
        }

        pizzaOrder.setOrderStatus(orderStatus);
        pizzaOrderRepository.save(pizzaOrder);
    }

    public void delete(UUID orderUuid) throws OrderStatusNotFoundException {
        PizzaOrder pizzaOrder = pizzaOrderRepository.findByUuid(orderUuid);

        if (pizzaOrder == null) {
            throw new PizzaOrderNotFoundException();
        }

        pizzaOrderRepository.delete(pizzaOrder);
    }

}
