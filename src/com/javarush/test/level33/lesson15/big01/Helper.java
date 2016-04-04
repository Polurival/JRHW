package com.javarush.test.level33.lesson15.big01;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by
 * Polurival on 04.04.2016.
 */
public class Helper
{
    public static String generateRandomString()
    {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    public static void printMessage(String message)
    {
        System.out.println(message);
    }
}
