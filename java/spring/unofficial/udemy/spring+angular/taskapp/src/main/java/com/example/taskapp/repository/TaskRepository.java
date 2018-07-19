package com.example.taskapp.repository;

import com.example.taskapp.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
