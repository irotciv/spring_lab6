package com.example.springLab5.repository;

import com.example.springLab5.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long id);
    List<Order> getOrders();
    boolean delete(Order order);
}
