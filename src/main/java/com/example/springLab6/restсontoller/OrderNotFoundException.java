package com.example.springLab6.restсontoller;

public class OrderNotFoundException extends RuntimeException{
    OrderNotFoundException(Long id){
        super("Could not find the order with id " + id);
    }
}
