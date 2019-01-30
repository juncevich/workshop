package com.example.udemy.go_fullstack.backend.basic.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationBean {
    private String message;

    public String toString() {
        return String.format("HelloWorldBean [message=%s]", message);
    }
}
