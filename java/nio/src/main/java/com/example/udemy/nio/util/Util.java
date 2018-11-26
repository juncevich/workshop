package com.example.udemy.nio.util;

import java.nio.ByteBuffer;

public class Util {
    private Util() {
    }

    public static int transmogrify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }

    public static void transmogrify(ByteBuffer buffer) {
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            buffer.put(i, (byte) transmogrify(buffer.get(i)));
        }
    }

}
