package com.example.springLab5.repository;

import com.example.springLab5.entity.MenuItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository {
    List<MenuItems> getMenu();
}
