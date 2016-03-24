package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);

            if (message.contains(": "))
            {
                String userName = message.split(": ")[0];
                String messageText = message.split(": ")[1];

                Calendar calendar = new GregorianCalendar();
                Date date = calendar.getTime();

                switch (messageText)
                {
                    case "дата":
                        sendTextMessage("Информация для " + userName + ": "
                                + new SimpleDateFormat("d.MM.YYYY", Locale.ENGLISH).format(date));
                        break;
                    case "день":
                        sendTextMessage("Информация для " + userName + ": "
                                + new SimpleDateFormat("d", Locale.ENGLISH).format(date));
                        break;
                    case "месяц":
                        sendTextMessage("Информация для " + userName + ": "
                                + new SimpleDateFormat("MMMM", Locale.ENGLISH).format(date));
                        break;
                    case "год":
                        sendTextMessage("Информация для " + userName + ": "
                                + new SimpleDateFormat("YYYY", Locale.ENGLISH).format(date));
                        break;
                    case "время":
                        sendTextMessage("Информация для " + userName + ": "
                                + new SimpleDateFormat("H:mm:ss", Locale.ENGLISH).format(date));
                        break;
                    case "час":
                        sendTextMessage("Информация для " + userName + ": "
                                + new SimpleDateFormat("H", Locale.ENGLISH).format(date));
                        break;
                    case "минуты":
                        sendTextMessage("Информация для " + userName + ": "
                                + new SimpleDateFormat("m", Locale.ENGLISH).format(date));
                        break;
                    case "секунды":
                        sendTextMessage("Информация для " + userName + ": "
                                + new SimpleDateFormat("s", Locale.ENGLISH).format(date));
                        break;
                }
            }
        }
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
            for (int i = 0; i < 100; i++)
            {
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
