package com.javarush.test.level30.lesson15.big01.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by
 * Polurival on 23.03.2016.
 */
public class BotClient extends Client
{
    private static List<Integer> botNumbers = new ArrayList<>();
    private static boolean botNumbersIsInitialized = false;

    public class BotSocketThread extends SocketThread
    {

    }

    public static void main(String[] args)
    {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    @Override
    protected SocketThread getSocketThread()
    {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole()
    {
        return false;
    }

    @Override
    protected String getUserName()
    {
        return "date_bot_" + getCurrentBotNumber();
    }

    private int getCurrentBotNumber()
    {
        if (!botNumbersIsInitialized)
        {
            for (int i = 0; i < 100; i++) {
                botNumbers.add(i);
            }
            Collections.shuffle(botNumbers);
            botNumbersIsInitialized = true;
        }
        int currentBotNumber = botNumbers.get(new Random().nextInt(botNumbers.size()));
        botNumbers.remove(currentBotNumber);
        return currentBotNumber;
    }
}
