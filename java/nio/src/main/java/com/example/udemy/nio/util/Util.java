package com.example.udemy.nio.util;

public class Util {
    private Util() {
    }

    public static int transmogrify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }
}
