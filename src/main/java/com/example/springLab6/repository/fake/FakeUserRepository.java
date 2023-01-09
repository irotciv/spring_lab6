package com.example.springLab6.repository.fake;

import com.example.springLab6.entity.User;
import com.example.springLab6.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
@Setter
public class FakeUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();

    public FakeUserRepository() {
        users.add(new User(0L, "john@gmail.com", "$2a$08$UnToTL1tsdZijK.nKaGeFO6nNtHlznJWBf.izk48yAeuBQagogj3i", "John", "Bill"));
        users.add(new User(1L, "bob@gmail.com", "$2a$08$F8cSKAN16de8rbe5V6zbUOBmGQXGd3Im3fAAUOk4fqEMp4B6EeyPi", "Bob", "Brown"));
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream().filter(user -> email.equals(user.getEmail())).findAny();
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream().filter(user -> id.equals(user.getId())).findAny();
    }
}
