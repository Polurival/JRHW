package com.javarush.test.level27.lesson09.home02;

public class Mail {
    private String text;

    public synchronized String getText() {
        return text;
    }

    public synchronized void setText(String text) {
        this.text = text;
    }
}
