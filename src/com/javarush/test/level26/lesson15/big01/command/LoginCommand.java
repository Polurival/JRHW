package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Polurival on 25.02.2016.
 */
class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle("com.javarush.test.level26.lesson15.big01.resources.verifiedCards", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException
    {
        Enumeration<String> cardNumbers = validCreditCards.getKeys();

        while (true) {
            System.out.println("Enter number of card");
            String cardNumber = ConsoleHelper.readString();
            System.out.println("Enter pin-code");
            String pinCode = ConsoleHelper.readString();
            if (cardNumber.matches("\\d{12}") && pinCode.matches("\\d{4}")) {
                boolean isLogin = false;
                while(cardNumbers.hasMoreElements()) {
                    String c = cardNumbers.nextElement();
                    String p = validCreditCards.getString(c);
                    if (c.equals(cardNumber) && p.equals(pinCode)) {
                        System.out.println("Welcome dear user!");
                        isLogin = true;
                        break;
                    }
                }
                if (isLogin) {
                    break;
                }
            } else {
                System.out.println("Incorrect number of card or pin-code");
            }
        }
    }
}
