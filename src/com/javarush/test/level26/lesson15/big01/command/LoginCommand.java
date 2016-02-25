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
    private ResourceBundle res = ResourceBundle.getBundle("com.javarush.test.level26.lesson15.big01.resources.login_en", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException
    {
        Enumeration<String> cardNumbers = validCreditCards.getKeys();

        System.out.println(res.getString("before"));
        System.out.println(res.getString("specify.data"));

        int tries = 0;
        while (true) {
            if (tries > 0) {
                System.out.println(res.getString("try.again.with.details"));
            }
            String cardNumber = ConsoleHelper.readString();
            String pinCode = ConsoleHelper.readString();
            if (cardNumber.matches("\\d{12}") && pinCode.matches("\\d{4}")) {
                boolean isLogin = false;
                while(cardNumbers.hasMoreElements()) {
                    String c = cardNumbers.nextElement();
                    String p = validCreditCards.getString(c);
                    if (c.equals(cardNumber) && p.equals(pinCode)) {
                        isLogin = true;
                        break;
                    }
                }
                if (isLogin) {
                    System.out.println(res.getString("success.format"));
                    break;
                }
            } else {
                System.out.println(String.format(res.getString("not.verified.format"), cardNumber));
                System.out.println(res.getString("try.again.or.exit"));
                tries++;
            }
        }
    }
}
