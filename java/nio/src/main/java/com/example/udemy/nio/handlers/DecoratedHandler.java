package com.example.udemy.nio.handlers;

import java.io.IOException;

public abstract class DecoratedHandler<S> implements Handler<S> {
    private final Handler<S> other;

    DecoratedHandler(Handler<S> other) {
        this.other = other;
    }

    @Override
    public void handle(S s) throws IOException {
        other.handle(s);
    }
}
