package com.example.springLab5.repository;

import com.example.springLab5.entity.MenuItems;

import java.util.List;
import java.util.Optional;

public interface MenuItemsRepository {
    Optional<MenuItems> findById(Long id);
    List<MenuItems> getMenuItems();
    List<MenuItems> findAll(Integer price, String description);
    List<MenuItems> findPaginated(Integer price, String description, Integer page, Integer size);
    void save(MenuItems menuItem);
    void update(MenuItems menuItem);
    boolean delete(MenuItems menuItem);
}
