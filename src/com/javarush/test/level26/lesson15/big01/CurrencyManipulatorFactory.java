package com.javarush.test.level26.lesson15.big01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Polurival
 * on 18.02.2016.
 */
public class CurrencyManipulatorFactory
{
    private static List<CurrencyManipulator> manipulators = new ArrayList<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        for (CurrencyManipulator cm : manipulators) {
            if (cm.getCurrencyCode().equals(currencyCode)) {
                return cm;
            }
        }
        CurrencyManipulator newCM = new CurrencyManipulator(currencyCode);
        manipulators.add(newCM);
        return newCM;
    }
}
