package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by
 * Polurival on 21.03.2016.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread
    {
        private Socket socket;

        public Handler(Socket socket)
        {
            this.socket = socket;
        }
    }

    public static void main(String[] args)
    {
        int port = Integer.valueOf(ConsoleHelper.readString());
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            ConsoleHelper.writeMessage("Server is running");
            while (true)
            {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage(e.getMessage());
        }

    }

    public static void sendBroadcastMessage(Message message)
    {
        for (Connection connection : connectionMap.values())
        {
            try
            {
                connection.send(message);
            }
            catch (IOException e)
            {
                ConsoleHelper.writeMessage("Message sending error");
            }
        }
    }
}
