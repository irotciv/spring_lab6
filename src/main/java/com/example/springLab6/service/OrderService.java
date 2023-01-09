package com.example.springLab6.service;

import com.example.springLab6.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> getAllOrders();

    void delete(Order order);

    Order getOrderById(Long id);

    Order addNewOrder(Order order, Long id);
}
