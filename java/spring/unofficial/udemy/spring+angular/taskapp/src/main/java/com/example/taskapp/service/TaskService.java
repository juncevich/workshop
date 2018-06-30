package com.example.taskapp.service;

import com.example.taskapp.domain.Task;


public interface TaskService {
    Iterable<Task> list();

    Task save(Task task);
}
