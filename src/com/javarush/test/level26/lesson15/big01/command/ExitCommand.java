package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

/**
 * Created by Polurival on 21.02.2016.
 */
class ExitCommand implements Command
{
    @Override
    public void execute() throws InterruptOperationException
    {
        System.out.println("Do you really want to exit? <y,n>");
        String answer = ConsoleHelper.readString();
        if ("y".equals(answer)) {
            System.out.println("Goodbye!");
        }
    }
}
