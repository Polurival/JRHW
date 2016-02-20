package com.javarush.test.level26.lesson15.big01;

import java.util.Locale;

/**
 * Created by Polurival
 * on 18.02.2016.
 */
public class CashMachine
{
    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);

        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] twoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        cm.addAmount(Integer.valueOf(twoDigits[0]), Integer.valueOf(twoDigits[1]));
    }
}
