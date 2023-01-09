package com.example.springLab6.service;

import com.example.springLab6.entity.MenuItems;
import com.example.springLab6.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository MenuRepository) {
        this.menuRepository = MenuRepository;
    }

    @Override
    public List<MenuItems> getMenu() {
        return menuRepository.getMenu();
    }

}
