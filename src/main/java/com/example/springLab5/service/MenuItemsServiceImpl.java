package com.example.springLab5.service;

import com.example.springLab5.entity.MenuItems;
import com.example.springLab5.repository.MenuItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuItemsServiceImpl implements MenuItemsService {
    private final MenuItemsRepository jdbcMenuItemsRepository;

    @Autowired
    public MenuItemsServiceImpl(MenuItemsRepository jdbcMenuItemsRepository) {
        this.jdbcMenuItemsRepository = jdbcMenuItemsRepository;
    }

    @Override
    public MenuItems getMenuItemById(Long id) {
        return jdbcMenuItemsRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public MenuItems saveMenuItem(MenuItems menuItem) {
        jdbcMenuItemsRepository.save(menuItem);
        return menuItem;
    }

    @Transactional
    @Override
    public MenuItems updateMenuItem(MenuItems oldMenuItem, MenuItems menuItem) {
        jdbcMenuItemsRepository.update(menuItem);
        return menuItem;
    }

    @Transactional
    @Override
    public boolean deleteMenuItem(MenuItems menuItem) {
        return jdbcMenuItemsRepository.delete(menuItem);
    }

    @Override
    public List<MenuItems> getMenuItems() {
        return jdbcMenuItemsRepository.getMenuItems();
    }

    @Override
    public List<MenuItems> findAll(Integer price, String description) {
        return jdbcMenuItemsRepository.findAll(price, description);
    }

    @Override
    public List<MenuItems> findPaginated(Integer price, String description, Integer page, Integer size) {
        return jdbcMenuItemsRepository.findPaginated(price, description, page, size);
    }
}
