package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Polurival on 21.02.2016.
 */
class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle("com.javarush.test.level26.lesson15.big01.resources.withdraw_en", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException
    {
        System.out.println(res.getString("before"));

        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true)
        {
            try
            {
                System.out.println(res.getString("specify.amount"));
                int sum = Integer.parseInt(ConsoleHelper.readString());
                if (sum <= 0)
                {
                    throw new NumberFormatException();
                }
                if (!currencyManipulator.isAmountAvailable(sum))
                {
                    System.out.println(res.getString("exact.amount.not.available"));
                    continue;
                }
                Map<Integer, Integer> currencyMap = currencyManipulator.withdrawAmount(sum);
                for (Map.Entry<Integer, Integer> pair : currencyMap.entrySet())
                {
                    System.out.println("\t" + pair.getKey() + " - " + pair.getValue());
                }
                System.out.println(String.format(res.getString("success.format"), sum, currencyCode));
                break;

            }
            catch (NumberFormatException e)
            {
                System.out.println(res.getString("specify.not.empty.amount"));
            }
            catch (NotEnoughMoneyException e)
            {
                System.out.println(res.getString("not.enough.money"));
            }
            catch (ConcurrentModificationException ignore)
            {
            }
        }
    }
}
