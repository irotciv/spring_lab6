package com.example.springLab6.service;

import com.example.springLab6.pojo.UserRegistrationRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AuthService {
    Map<String, Object> processRegister(UserRegistrationRequest request);
}
