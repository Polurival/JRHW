package com.javarush.test.level26.lesson15.big01;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Polurival
 * on 18.02.2016.
 */
public class CurrencyManipulator
{
    private String currencyCode;

    Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        Integer currencyCount = denominations.get(denomination);
        if (currencyCount != null) {
            denominations.put(denomination, currencyCount + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()) {
            sum += (pair.getKey() * pair.getValue());
        }

        return sum;
    }
}
