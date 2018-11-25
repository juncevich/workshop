package com.example.udemy.nio.handlers;

import java.io.IOException;
import java.io.UncheckedIOException;

public class UncheckedIOExceptionConverterHandler<S> implements Handler<S> {
    private final Handler<S> other;

    UncheckedIOExceptionConverterHandler(Handler<S> other) {
        this.other = other;
    }


    @Override
    public void handle(S s) {
        try {
            other.handle(s);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
