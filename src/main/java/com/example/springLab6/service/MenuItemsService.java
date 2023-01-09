package com.example.springLab6.service;

import com.example.springLab6.entity.MenuItems;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuItemsService {
    MenuItems saveMenuItem(MenuItems menuItem);

    MenuItems getMenuItemById(Long id);

    MenuItems updateMenuItem(MenuItems oldMenuItem, MenuItems menuItem);

    boolean deleteMenuItem(MenuItems menuItem);

    List<MenuItems> getMenuItems();

    List<MenuItems> findAll(Integer price, String description);

    List<MenuItems> findPaginated(Integer price, String description, Integer page, Integer size);
}
