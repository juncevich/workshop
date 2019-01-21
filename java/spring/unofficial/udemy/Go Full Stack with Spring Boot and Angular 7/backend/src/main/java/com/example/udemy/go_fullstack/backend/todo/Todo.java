package com.example.udemy.go_fullstack.backend.todo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Todo {
    private int id;
    private String username;
    private String description;
    private Date targetDate;
    private boolean isDone;
}
