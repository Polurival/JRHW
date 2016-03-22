package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;

/**
 * Created by
 * Polurival on 22.03.2016.
 */
public class Client
{
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread
    {

    }

    public static void main(String[] args)
    {
        Client client = new Client();
        client.run();
    }

    public void run()
    {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();

        try
        {
            synchronized (this)
            {
                this.wait();
            }
        }
        catch (InterruptedException e)
        {
            ConsoleHelper.writeMessage("Connection has been interrupted");
            return;
        }

        if (clientConnected)
        {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        } else
        {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
        String textMessage;
        while (clientConnected)
        {
            textMessage = ConsoleHelper.readString();
            if ("exit".equals(textMessage))
            {
                break;
            }
            if (shouldSentTextFromConsole())
            {
                sendTextMessage(textMessage);
            }
        }
    }

    protected String getServerAddress()
    {
        return ConsoleHelper.readString();
    }

    protected int getServerPort()
    {
        return ConsoleHelper.readInt();
    }

    protected String getUserName()
    {
        return ConsoleHelper.readString();
    }

    protected boolean shouldSentTextFromConsole()
    {
        return true;
    }

    protected SocketThread getSocketThread()
    {
        return new SocketThread();
    }

    protected void sendTextMessage(String text)
    {
        Message textMessage = new Message(MessageType.TEXT, text);
        try
        {
            connection.send(textMessage);
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Message sending to Server error");
            clientConnected = false;
        }
    }
}
