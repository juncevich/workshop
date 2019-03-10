package com.example.udemy.go_fullstack.backend.db.repository;

import com.example.udemy.go_fullstack.backend.db.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUsername(String username);
}
