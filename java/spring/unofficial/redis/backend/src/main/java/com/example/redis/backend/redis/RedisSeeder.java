package com.example.redis.backend.redis;

import com.example.redis.backend.redis.model.Role;
import com.example.redis.backend.redis.model.User;
import com.example.redis.backend.redis.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisSeeder implements CommandLineRunner {

    private final UserRepository repository;
    private final RedisTemplate  redisTemplate;

    @Override
    public void run(String... args) {
        final var user = User.builder()
                .id(UUID.randomUUID())
                .role(Role.USER)
                .userName("Test User")
                .build();
        repository.save(user);
        for (int i = 0; i < 100; i++) {
            final var testUser = User.builder()
                    .id(UUID.randomUUID())
                    .role(Role.USER)
                    .userName("Test User")
                    .build();
            repository.save(testUser);
        }

        log.warn(repository.findAll().toString());
    }

}
