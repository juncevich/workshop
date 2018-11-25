package com.example.udemy.nio.server;

import com.example.udemy.nio.handlers.Handler;
import com.example.udemy.nio.handlers.PrintingHandler;
import com.example.udemy.nio.handlers.TransmogrifyHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class SingleThreadedBlockingServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket ss = new ServerSocket(8080)) {
            Handler<Socket> handler = new PrintingHandler<>(new TransmogrifyHandler());

            while (true) {
                Socket s = ss.accept();
                handler.handle(s);
            }
        }
    }
}
