package com.example.springLab5.repository.fake;

import com.example.springLab5.entity.Order;
import com.example.springLab5.repository.MenuItemsRepository;
import com.example.springLab5.repository.OrderRepository;
import com.example.springLab5.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
@Setter
public class FakeOrderRepository implements OrderRepository {
    private UserRepository fakeUserRepository;
    private MenuItemsRepository fakeMenuItemsRepository;

    private final List<Order> orders = new ArrayList<>();

    public FakeOrderRepository(UserRepository fakeUserRepository, MenuItemsRepository fakeMenuItemsRepository) {
        this.fakeUserRepository = fakeUserRepository;
        this.fakeMenuItemsRepository = fakeMenuItemsRepository;
        orders.add(new Order(0L, fakeUserRepository.getUsers().get(0), fakeMenuItemsRepository.getMenuItems().get(0)));
        orders.add(new Order(1L, fakeUserRepository.getUsers().get(1), fakeMenuItemsRepository.getMenuItems().get(1)));

//        orders.add(new Order(fakeUserRepository.getUsers().get(0),
//                new ArrayList<MenuItems>(){fakeMenuItemsRepository.getMenuItems().get(0), }, 200));
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orders.stream().filter(order -> id.equals(order.getId())).findAny();
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean delete(Order order) {
        return orders.remove(order);
    }
}
