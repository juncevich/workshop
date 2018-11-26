package com.example.udemy.nio.server;

import com.example.udemy.nio.handlers.BlockingChannelHandler;
import com.example.udemy.nio.handlers.ExecutorServiceHandler;
import com.example.udemy.nio.handlers.Handler;
import com.example.udemy.nio.handlers.PrintingHandler;
import com.example.udemy.nio.handlers.TransmogrifyChannelHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;

@Slf4j
public class BlockingNIOServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
            ssc.bind(new InetSocketAddress(8080));
            Handler<SocketChannel> handler =
                    new ExecutorServiceHandler<>(
                            new PrintingHandler<>(
                                    new BlockingChannelHandler(
                                            new TransmogrifyChannelHandler()
                                    ))
                            , Executors.newCachedThreadPool(), (t, e) -> log.error("Uncaught: {} error: {}", t, e.getMessage()));
            while (true) {
                SocketChannel s = ssc.accept();
                handler.handle(s);
            }
        }
    }

}
