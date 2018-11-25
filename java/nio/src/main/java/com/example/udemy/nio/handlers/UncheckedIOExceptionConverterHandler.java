package com.example.udemy.nio.handlers;

import java.io.IOException;
import java.io.UncheckedIOException;

public class UncheckedIOExceptionConverterHandler<S> extends DecoratedHandler<S> {

    UncheckedIOExceptionConverterHandler(Handler<S> other) {
        super(other);
    }


    @Override
    public void handle(S s) {
        try {
            super.handle(s);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
