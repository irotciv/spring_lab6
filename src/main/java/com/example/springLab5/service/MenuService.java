package com.example.springLab5.service;


import com.example.springLab5.entity.MenuItems;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    List<MenuItems> getMenu();
}
