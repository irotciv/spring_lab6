package com.example.springLab6.entity;

import javax.persistence.*;

@Entity
public class Menu {
    private Long id;
    private MenuItems menuItems;
    private User user;
}
