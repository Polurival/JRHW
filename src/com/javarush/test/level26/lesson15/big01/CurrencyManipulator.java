package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Polurival
 * on 18.02.2016.
 */
public class CurrencyManipulator
{
    private String currencyCode;

    private Map<Integer, Integer> denominations = new HashMap<>();

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

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {

        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> sortedDenominations = new TreeMap<>(Collections.reverseOrder());
        sortedDenominations.putAll(denominations);
        Map<Integer, Integer> sortedWithdraw = new TreeMap<>(Collections.reverseOrder());

        int sum = expectedAmount;
        if (sum == 0) {
            try
            {
                throw new NullPointerException();
            } catch (NullPointerException e) {
                System.out.println("Incorrect data. 0");
            }
        }
        for (Map.Entry<Integer, Integer> pair : sortedDenominations.entrySet()) {
            int count = sum / pair.getKey();
            if (count > 0) {
                sortedWithdraw.put(pair.getKey(), count);
                sum -= pair.getKey() * count;

                denominations.put(pair.getKey(), pair.getValue() - count);
                if (denominations.get(pair.getKey()) == 0) {
                    denominations.remove(pair.getKey());
                }
            }
        }
        if (sum != 0) {
            throw new NotEnoughMoneyException();
        }

        return sortedWithdraw;
    }
}
