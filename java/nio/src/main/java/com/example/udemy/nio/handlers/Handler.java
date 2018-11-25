package com.example.udemy.nio.handlers;

import java.io.IOException;

public interface Handler<S> {
    void handle(S s) throws IOException;
}
