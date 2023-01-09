package com.example.springLab6.repository;

import com.example.springLab6.entity.MenuItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository {
    List<MenuItems> getMenu();
}
