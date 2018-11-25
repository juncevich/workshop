package com.example.udemy.nio.server;

import com.example.udemy.nio.handlers.ExecutorServiceHandler;
import com.example.udemy.nio.handlers.Handler;
import com.example.udemy.nio.handlers.PrintingHandler;
import com.example.udemy.nio.handlers.TransmogrifyHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

@Slf4j
public class ExecutorServiceBlockingServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket ss = new ServerSocket(8080)) {
            Handler<Socket> handler =
                    new ExecutorServiceHandler<>(
                            new PrintingHandler<>(
                                    new TransmogrifyHandler()
                            )
                            , Executors.newCachedThreadPool(), (t, e) -> log.error("Uncaught: {} error: {}", t, e.getMessage()));
            while (true) {
                Socket s = ss.accept();
                handler.handle(s);
            }
        }
    }
}
