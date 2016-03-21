package com.javarush.test.level30.lesson15.big01;

import java.io.Serializable;

/**
 * Created by
 * Polurival on 21.03.2016.
 */
public class Message implements Serializable
{
    private final MessageType type;
    private final String data;

    public MessageType getType()
    {
        return type;
    }

    public String getData()
    {
        return data;
    }

    public Message(MessageType type, String data)
    {
        this.type = type;
        this.data = data;
    }

    public Message(MessageType type)
    {
        this(type, null);
    }
}
