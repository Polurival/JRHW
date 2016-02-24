package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.Map;

/**
 * Created by Polurival on 21.02.2016.
 */
class WithdrawCommand implements Command
{
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while(true){
            try
            {
                ConsoleHelper.writeMessage("Введите сумму:");
                int sum = Integer.parseInt(ConsoleHelper.readString());
                if(sum <= 0){
                    throw new NumberFormatException();
                }
                if(!currencyManipulator.isAmountAvailable(sum)){
                    continue;
                }
                Map<Integer, Integer> currencyMap = currencyManipulator.withdrawAmount(sum);
                for(Map.Entry<Integer, Integer> pair : currencyMap.entrySet()){
                    ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
                }
                ConsoleHelper.writeMessage("Транзакция прошла успешно.");
                break;

            }catch(NumberFormatException e){
                ConsoleHelper.writeMessage("Введены некорректные данные.");
            }
            catch (NotEnoughMoneyException e)
            {
                ConsoleHelper.writeMessage("В банкомате недостаточно банкнот.");
            }
            catch(ConcurrentModificationException ignore){}
        }
    }
}
