package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

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

    public static String readString() throws InterruptOperationException
    {
        String message = null;
        try {
            message = reader.readLine();
        } catch (Exception e) {
        }
        if (message != null)
        {
            if ("EXIT".equals(message.toUpperCase()))
            {
                throw new InterruptOperationException();
            }
        }
        return message;
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        while (true) {
            writeMessage("Enter currency code");
            String currencyCode = readString();
            if (currencyCode.length() == 3
                    && Character.isLetter(currencyCode.toCharArray()[0])
                    && Character.isLetter(currencyCode.toCharArray()[1])
                    && Character.isLetter(currencyCode.toCharArray()[2])) {
                currencyCode = currencyCode.toUpperCase();
                return currencyCode;
            } else {
                writeMessage("Incorrect data");
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        while (true) {
            writeMessage("Enter two integer positive digits");
            String[] temp = readString().split(" ");
            if (temp[0].matches("\\d+") && temp[1].matches("\\d+")) {
                return temp;
            } else {
                writeMessage(currencyCode);
            }
        }

    }

    public static Operation askOperation() throws InterruptOperationException
    {
        while (true)
        {
            try
            {
                System.out.println("Enter 1 (INFO), 2 (DEPOSIT), 3 (WITHDRAW), 4 (EXIT)");
                return Operation.getAllowableOperationByOrdinal(Integer.valueOf(readString()));
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Incorrect operation");
            }
        }
    }
}
