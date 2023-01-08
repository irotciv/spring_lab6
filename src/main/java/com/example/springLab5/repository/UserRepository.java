package com.example.springLab5.repository;

import com.example.springLab5.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getUsers();
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}
