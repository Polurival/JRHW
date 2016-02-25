package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Polurival
 * on 18.02.2016.
 */
public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle("com.javarush.test.level26.lesson15.big01.resources.common_en", Locale.ENGLISH);

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
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        }
        return message;
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String currencyCode = readString();
            if (currencyCode.length() == 3
                    && Character.isLetter(currencyCode.toCharArray()[0])
                    && Character.isLetter(currencyCode.toCharArray()[1])
                    && Character.isLetter(currencyCode.toCharArray()[2])) {
                currencyCode = currencyCode.toUpperCase();
                return currencyCode;
            } else {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String[] temp = readString().split(" ");
            if (temp[0].matches("\\d+") && temp[1].matches("\\d+")) {
                return temp;
            } else {
                writeMessage(res.getString("invalid.data"));
            }
        }

    }

    public static Operation askOperation() throws InterruptOperationException
    {
        while (true)
        {
            try
            {
                writeMessage(res.getString("choose.operation"));
                Operation op = Operation.getAllowableOperationByOrdinal(Integer.valueOf(readString()));
                switch (op) {
                    case INFO:
                        writeMessage(res.getString("operation.INFO"));
                        break;
                    case DEPOSIT:
                        writeMessage(res.getString("operation.DEPOSIT"));
                        break;
                    case WITHDRAW:
                        writeMessage(res.getString("operation.WITHDRAW"));
                        break;
                    case EXIT:
                        writeMessage(res.getString("operation.EXIT"));
                        break;
                }
                return op;
            }
            catch (IllegalArgumentException e)
            {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }
}
