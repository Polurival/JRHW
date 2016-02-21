package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;

/**
 * Created by Polurival on 21.02.2016.
 */
class InfoCommand implements Command
{
    @Override
    public void execute()
    {
        Collection<CurrencyManipulator> bills = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        boolean hasMoney = false;

        for (CurrencyManipulator bill : bills) {
            if (bill.hasMoney()) {
                hasMoney = true;
                System.out.println(bill.getCurrencyCode() + " - " + bill.getTotalAmount());
            }
        }

        if (!hasMoney) {
            System.out.println("No money available.");
        }
    }
}
