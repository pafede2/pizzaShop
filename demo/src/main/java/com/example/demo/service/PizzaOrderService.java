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

    /*
    Convert PizzaOrder (DB object, entity) to OrderOutput object ready to sent as a request result
     */
    private OrderOutput orderFromDbToOutput(PizzaOrder order) {
        return new OrderOutput.OrderOutputBuilder()
                .withUuid(order.getUuid().toString())
                .withIsPayed(order.getPaymentStatus())
                .withOrderStatus(order.getOrderStatus().getName())
                .withPayOption(order.getPayOption().getName())
                .withPizzas(order.getPizzas()
                        .stream()
                        .map(pizza -> new PizzaOutput.PizzaOutputBuilder()
                            .withPizzaType(pizza.getPizzaType().getName())
                            .withPrice(pizza.getPizzaType().getPrice())
                            .withToppings(pizza.getToppings()
                                    .stream()
                                    .map(topping -> new ToppingOutput.ToppingOutputBuilder()
                                            .withName(topping.getName())
                                            .withPrice(topping.getPrice())
                                            .build()
                                    ).collect(Collectors.toList()))
                            .build()

                        ).collect(Collectors.toList()))
                .withCustomerOutput(new CustomerOutput(order.getCustomer().getUuid(), order.getCustomer().getFirstName(), order.getCustomer().getLastName()))
                .withDeliveryAddress(order.getDeliveryAddress())
                .build();
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

            pizzas.add(new Pizza.PizzaBuilder()
                    .withPizzaType(pizzaTypes.get(pizzaInOrder.getPizzaType()))
                    .withTopping(toppingsForPizza)
                    .build());
        });

        PizzaOrder pizza = pizzaOrderRepository.save(new PizzaOrder.PizzaOrderBuilder()
                .withCustomer(customer)
                .withPayOption(payOption)
                .withOrderStatus(orderStatus)
                .withPizzas(pizzas)
                .withDeliveryAddress(order.getDeliveryAddress())
                .build());

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

        if (order.getDeliveryAddress() == null) {
            throw new DeliveryAddressNotNullException();
        }

        pizzaOrder.setOrderStatus(orderStatus);
        pizzaOrder.setDeliveryAddress(order.getDeliveryAddress());
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
