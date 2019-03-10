package com.example.udemy.go_fullstack.backend.service;

import com.example.udemy.go_fullstack.backend.db.model.*;
import com.example.udemy.go_fullstack.backend.db.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo findById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }
    
    public List<Todo> findByUsername( String username){
        return todoRepository.findByUsername(username);
    }
}
