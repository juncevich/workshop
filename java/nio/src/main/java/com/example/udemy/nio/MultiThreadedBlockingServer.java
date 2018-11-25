package com.example.udemy.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class MultiThreadedBlockingServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket ss = new ServerSocket(8080)) {
            while (true) {
                Socket s = ss.accept();
                handle(s);
            }
        }
    }

    private static void handle(Socket s) {
        new Thread(() -> {
            log.info("Connected to {}", s);
            try (
                    s;
                    InputStream in = s.getInputStream();
                    OutputStream out = s.getOutputStream()
            ) {
                int data;
                while ((data = in.read()) != -1) {
                    out.write(transmogrify(data));
                }

            } catch (IOException e) {
                throw new UncheckedIOException(e);
            } finally {
                log.info("Disconnected from {}", s);
            }
        }).start();
    }

    private static int transmogrify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }
}
