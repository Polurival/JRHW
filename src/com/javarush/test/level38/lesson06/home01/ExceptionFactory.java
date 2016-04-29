package com.javarush.test.level38.lesson06.home01;

/**
 * Created by
 * Polurival on 29.04.2016.
 */
public class ExceptionFactory
{
    public static Throwable getException(Enum type)
    {
        if (type != null)
        {
            String message = type.toString().substring(0, 1).toUpperCase()
                    + type.toString().substring(1).toLowerCase().replace("_", " ");

            if (type instanceof ExceptionApplicationMessage)
            {
                return new Exception(message);
            } else if (type instanceof ExceptionDBMessage)
            {
                return new RuntimeException(message);
            } else if (type instanceof ExceptionUserMessage)
            {
                return new Error(message);
            }
        }
        return new IllegalArgumentException();
    }
}
