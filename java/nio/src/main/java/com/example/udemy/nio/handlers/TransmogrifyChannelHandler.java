package com.example.udemy.nio.handlers;

import com.example.udemy.nio.util.Util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TransmogrifyChannelHandler implements Handler<SocketChannel> {

    @Override
    public void handle(SocketChannel sc) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(80);
        int read = sc.read(buffer);
        if (read == -1) {
            sc.close();
            return;
        }
        if (read > 0) {
            Util.transmogrify(buffer);
            while (buffer.hasRemaining()) {
                sc.write(buffer);
            }
            buffer.clear();
        }
    }
}
