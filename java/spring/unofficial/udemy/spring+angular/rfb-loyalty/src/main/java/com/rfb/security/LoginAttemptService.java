package com.rfb.security;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 10;
    private LoadingCache<String, Integer> attemptCache;

    public LoginAttemptService() {
        super();
        attemptCache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(
                new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) {
                        return 0;
                    }
                }

            );
    }

    public void loginSucceeded(String key) {
        attemptCache.invalidate(key);
    }

    public void loginFailed(String key) {
        int attempts;

        try {
            attempts = attemptCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }

        attempts++;
        attemptCache.put(key, attempts);
    }

    public boolean isBlocked(String key) {
        try {
            return attemptCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
