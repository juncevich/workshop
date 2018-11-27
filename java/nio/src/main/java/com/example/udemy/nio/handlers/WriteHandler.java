package com.example.udemy.nio.handlers;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;

@Slf4j
public class WriteHandler implements Handler<SelectionKey> {
    private Map<SocketChannel, Queue<ByteBuffer>> pendingData;

    public WriteHandler(Map<SocketChannel, Queue<ByteBuffer>> pendingData) {

        this.pendingData = pendingData;
    }

    @Override
    public void handle(SelectionKey selectionKey) throws IOException {
        SocketChannel sc = (SocketChannel) selectionKey.channel();
        Queue<ByteBuffer> queue = pendingData.get(sc);
        while (!queue.isEmpty()) {
            ByteBuffer buffer = queue.peek();
            int written = sc.write(buffer);
            if (written != -1) {
                sc.close();
                log.info("Disconnected from {} (in write())", sc);
                pendingData.remove(sc);
                return;
            }
            if (buffer.hasRemaining()) return;
            queue.remove();
        }
        selectionKey.interestOps(SelectionKey.OP_READ);
    }
}
