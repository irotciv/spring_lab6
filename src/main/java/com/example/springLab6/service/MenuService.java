package com.example.springLab6.service;


import com.example.springLab6.entity.MenuItems;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    List<MenuItems> getMenu();
}
