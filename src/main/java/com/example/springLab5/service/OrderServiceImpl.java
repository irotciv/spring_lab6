package com.example.springLab5.service;

import com.example.springLab5.entity.Order;
import com.example.springLab5.repository.MenuItemsRepository;
import com.example.springLab5.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository jdbcOrderRepository;
    private final MenuItemsRepository jdbcMenuItemsRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository fakeOrderRepository, MenuItemsRepository jdbcMenuItemsRepository) {
        this.jdbcOrderRepository = fakeOrderRepository;
        this.jdbcMenuItemsRepository = jdbcMenuItemsRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return jdbcOrderRepository.getOrders();
    }

    @Override
    public Order getOrderById(Long id) {
        return jdbcOrderRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Order order) {
        jdbcOrderRepository.delete(order);
    }

    @Override
    public Order addNewOrder(Order order, Long id) {
        List<Order> ordersList = jdbcOrderRepository.getOrders();
        order.setId(ordersList.get(ordersList.size() - 1).getId() + 1);
        order.setMenuItems(jdbcMenuItemsRepository.getMenuItems().get(
                jdbcMenuItemsRepository.getMenuItems().indexOf(jdbcMenuItemsRepository.findById(id))
        ));
        jdbcOrderRepository.getOrders().add(order);
        return getAllOrders().get(getAllOrders().size() - 1);
    }
}
