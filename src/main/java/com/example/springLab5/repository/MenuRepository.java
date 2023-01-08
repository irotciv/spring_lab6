package com.example.springLab5.repository;

import com.example.springLab5.entity.MenuItems;

import java.util.List;

public interface MenuRepository {
    List<MenuItems> getMenu();
}
