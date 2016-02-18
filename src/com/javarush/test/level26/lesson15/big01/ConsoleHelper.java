package com.javarush.test.level26.lesson15.big01;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Polurival
 * on 18.02.2016.
 */
public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String message = null;
        try {
            message = reader.readLine();
        } catch (Exception e) {

        }
        return message;
    }
}
