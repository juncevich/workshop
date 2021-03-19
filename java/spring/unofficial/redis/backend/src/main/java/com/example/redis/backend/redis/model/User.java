package com.example.redis.backend.redis.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Data
@RedisHash("User")
public class User implements Serializable {

    private UUID id;
    private String userName;
    private Role role;
}
