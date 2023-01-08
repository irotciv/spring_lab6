package com.example.springLab5.service;

import com.example.springLab5.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> getAllOrders();

    void delete(Order order);

    Order getOrderById(Long id);

    Order addNewOrder(Order order, Long id);
}
