package com.example.udemy.go_fullstack.backend.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelloWorldBean {
    private String message;

    public String toString() {
        return String.format("HelloWorldBean [message=%s]", message);
    }
}
