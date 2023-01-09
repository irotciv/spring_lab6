package com.example.springLab6.service;

import com.example.springLab6.entity.MenuItems;
import com.example.springLab6.repository.MenuItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuItemsServiceImpl implements MenuItemsService {
    private final MenuItemsRepository menuItemsRepository;

    @Autowired
    public MenuItemsServiceImpl(MenuItemsRepository menuItemsRepository) {
        this.menuItemsRepository = menuItemsRepository;
    }

    @Override
    public MenuItems getMenuItemById(Long id) {
        return menuItemsRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public MenuItems saveMenuItem(MenuItems menuItem) {
        menuItemsRepository.save(menuItem);
        return menuItem;
    }

    @Transactional
    @Override
    public MenuItems updateMenuItem(MenuItems oldMenuItem, MenuItems menuItem) {
        menuItemsRepository.update(menuItem);
        return menuItem;
    }

    @Transactional
    @Override
    public boolean deleteMenuItem(MenuItems menuItem) {
        return menuItemsRepository.delete(menuItem);
    }

    @Override
    public List<MenuItems> getMenuItems() {
        return menuItemsRepository.getMenuItems();
    }

    @Override
    public List<MenuItems> findAll(Integer price, String description) {
        return menuItemsRepository.findAll(price, description);
    }

    @Override
    public List<MenuItems> findPaginated(Integer price, String description, Integer page, Integer size) {
        return menuItemsRepository.findPaginated(price, description, page, size);
    }
}
