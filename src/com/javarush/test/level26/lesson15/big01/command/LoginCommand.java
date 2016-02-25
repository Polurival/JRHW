package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

/**
 * Created by Polurival on 25.02.2016.
 */
public class LoginCommand implements Command
{
    @Override
    public void execute() throws InterruptOperationException
    {
        String hardCodeCardNumber = "123456789012";
        String hardCodePinCode = "1234";

        while (true) {
            System.out.println("Enter number of card");
            String cardNumber = ConsoleHelper.readString();
            System.out.println("Enter pin-code");
            String pinCode = ConsoleHelper.readString();
            if (cardNumber.matches("\\d{12}") && pinCode.matches("\\d{4}")) {
                if (hardCodeCardNumber.equals(cardNumber) && hardCodePinCode.equals(pinCode)) {
                    System.out.println("Welcome dear user!");
                    break;
                }
            } else {
                System.out.println("Incorrect number of card or pin-code");
            }
        }
    }
}
