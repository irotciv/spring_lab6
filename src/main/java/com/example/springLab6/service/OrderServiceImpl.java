package com.example.springLab6.service;

import com.example.springLab6.entity.Order;
import com.example.springLab6.repository.MenuItemsRepository;
import com.example.springLab6.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MenuItemsRepository menuItemsRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository fakeOrderRepository, MenuItemsRepository menuItemsRepository) {
        this.orderRepository = fakeOrderRepository;
        this.menuItemsRepository = menuItemsRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getOrders();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public Order addNewOrder(Order order, Long id) {
        List<Order> ordersList = orderRepository.getOrders();
        order.setId(ordersList.get(ordersList.size() - 1).getId() + 1);
        order.setMenuItems(menuItemsRepository.getMenuItems().get(
                menuItemsRepository.getMenuItems().indexOf(menuItemsRepository.findById(id))
        ));
        orderRepository.getOrders().add(order);
        return getAllOrders().get(getAllOrders().size() - 1);
    }
}
