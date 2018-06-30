package com.example.taskapp;

import com.example.taskapp.domain.Task;
import com.example.taskapp.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class TaskappApplication {

    public static void main(String[] args) {

        SpringApplication.run(TaskappApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(TaskService taskService) {
        return args -> {
            taskService.save(new Task(1L, "Test task 1", LocalDate.now(), true));
            taskService.save(new Task(2L, "Test task 2", LocalDate.now().plus(1, ChronoUnit.DAYS), false));
            taskService.save(new Task(3L, "Test task 3", LocalDate.now().plus(2, ChronoUnit.DAYS), false));
            taskService.save(new Task(4L, "Test task 4", LocalDate.now().plus(5, ChronoUnit.DAYS), false));
            taskService.save(new Task(5L, "Test task 5", LocalDate.now().plus(7, ChronoUnit.DAYS), false));
            taskService.save(new Task(6L, "Test task 6", LocalDate.now().plus(8, ChronoUnit.DAYS), false));
            taskService.save(new Task(7L, "Test task 7", LocalDate.now().plus(10, ChronoUnit.DAYS), false));
            taskService.save(new Task(8L, "Test task 8", LocalDate.now().plus(11, ChronoUnit.DAYS), false));
        };
    }
}
