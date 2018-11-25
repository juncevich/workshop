package com.example.udemy.nio.handlers;

import com.example.udemy.nio.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TransmogrifyHandler implements Handler<Socket> {

    @Override
    public void handle(Socket s) throws IOException {
        try (
                s;
                InputStream in = s.getInputStream();
                OutputStream out = s.getOutputStream()
        ) {
            int data;
            while ((data = in.read()) != -1) {
                out.write(Util.transmogrify(data));
            }

        }
    }
}
