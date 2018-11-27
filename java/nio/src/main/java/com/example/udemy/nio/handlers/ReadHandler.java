package com.example.udemy.nio.handlers;

import com.example.udemy.nio.util.Util;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;

@Slf4j
public class ReadHandler implements Handler<SelectionKey> {
    private Map<SocketChannel, Queue<ByteBuffer>> pendingData;

    public ReadHandler(Map<SocketChannel, Queue<ByteBuffer>> pendingData) {

        this.pendingData = pendingData;
    }

    @Override
    public void handle(SelectionKey selectionKey) throws IOException {
        SocketChannel sc = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(80);
        int read = sc.read(buffer);
        if (read == -1) {
            pendingData.remove(sc);
            sc.close();
            log.info("Disconnected from {} (in read())", sc);
            return;
        }
        if (read > 0) {
            Util.transmogrify(buffer);
            pendingData.get(sc).add(buffer);
            selectionKey.interestOps(SelectionKey.OP_WRITE);
        }
    }
}
