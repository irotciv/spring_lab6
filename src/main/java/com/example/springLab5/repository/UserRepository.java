package com.example.springLab5.repository;

import com.example.springLab5.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> getUsers();
    Optional<User> findByEmail(String email);
}
