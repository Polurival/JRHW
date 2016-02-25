package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Polurival on 21.02.2016.
 */
class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle("com.javarush.test.level26.lesson15.big01.resources.info_en", Locale.ENGLISH);

    @Override
    public void execute()
    {
        Collection<CurrencyManipulator> bills = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        boolean hasMoney = false;

        for (CurrencyManipulator bill : bills) {
            if (bill.hasMoney()) {
                hasMoney = true;
                System.out.println(res.getString("before"));
                System.out.println(bill.getCurrencyCode() + " - " + bill.getTotalAmount());
            }
        }

        if (!hasMoney) {
            System.out.println(res.getString("no.money"));
        }
    }
}
