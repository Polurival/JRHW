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
