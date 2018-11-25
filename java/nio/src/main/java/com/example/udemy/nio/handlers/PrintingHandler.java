package com.example.udemy.nio.handlers;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class PrintingHandler<S> implements Handler<S> {
    private final Handler<S> other;

    public PrintingHandler(Handler<S> other) {
        this.other = other;
    }

    @Override
    public void handle(S s) throws IOException {
        log.info("Connected to {}", s);
        try {
            other.handle(s);
        } finally {
            log.info("Disconnected from {}", s);
        }
    }
}
