package com.example.udemy.nio.server;

import com.example.udemy.nio.handlers.Handler;
import com.example.udemy.nio.handlers.TransmogrifyChannelHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class SingleThreadedPoolingNonBlockingServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
            ssc.bind(new InetSocketAddress(8080));
            ssc.configureBlocking(false);
            Handler<SocketChannel> handler =
                    new TransmogrifyChannelHandler();
            Collection<SocketChannel> sockets = new ArrayList<>();
            while (true) {
                SocketChannel sc = ssc.accept();
                if (sc != null) {
                    sockets.add(sc);
                    log.info("Connected to: {}", sc);
                    sc.configureBlocking(false);
                }
                for (SocketChannel socket : sockets) {
                    if (socket.isConnected()) {
                        handler.handle(socket);
                    }
                }

                sockets.removeIf(socket -> !socket.isConnected());

            }
        }
    }

}
