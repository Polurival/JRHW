package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

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

    public void addAmount(int denomination, int count)
    {
        Integer currencyCount = denominations.get(denomination);
        if (currencyCount != null)
        {
            denominations.put(denomination, currencyCount + count);
        } else
        {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount()
    {
        int sum = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet())
        {
            sum += (pair.getKey() * pair.getValue());
        }

        return sum;
    }

    public boolean hasMoney()
    {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount)
    {

        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        Map<Integer, Integer> resultMap = new TreeMap<>(Collections.reverseOrder());
        Map<Integer, Integer> tempMap = new HashMap<>(denominations);
        List<Integer> denominationsKeyList = new ArrayList<>(tempMap.keySet());
        Collections.sort(denominationsKeyList, Collections.<Integer>reverseOrder());

        for (int i = 0; i < denominationsKeyList.size(); i++)
        {
            int nominal = denominationsKeyList.get(i);
            int countOfNominal = tempMap.get(nominal);
            int countOfNominalForPut = 0;

            for (int k = 0; k < countOfNominal; k++)
            {
                if (expectedAmount >= nominal)
                {
                    countOfNominalForPut++;
                    expectedAmount -= nominal;
                } else
                {
                    break;
                }
            }
            if (countOfNominalForPut != 0)
            {
                if (countOfNominal - countOfNominalForPut == 0)
                {
                    tempMap.remove(nominal);
                } else
                {
                    tempMap.put(nominal, countOfNominal - countOfNominalForPut);
                }
                resultMap.put(nominal, countOfNominalForPut);
            }
        }

        if (expectedAmount != 0) throw new NotEnoughMoneyException();

        denominations.clear();
        denominations.putAll(tempMap);

        return resultMap;
    }
}

