package com.javarush.test.level30.lesson15.big01;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by
 * Polurival on 21.03.2016.
 */
public class Connection implements Closeable
{
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public Connection(Socket socket) throws IOException
    {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message message) throws IOException
    {
        synchronized (out)
        {
            out.writeObject(message);
        }
    }

    public Message receive() throws IOException, ClassNotFoundException
    {
        synchronized (in)
        {
            return (Message) in.readObject();
        }
    }

    public SocketAddress getRemoteSocketAddress()
    {
        return socket.getRemoteSocketAddress();
    }

    public void close() throws IOException
    {
        socket.close();
        out.close();
        in.close();
    }
}
