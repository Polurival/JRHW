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

        public static void main(String[] args)
        {
            ConsoleHelper.writeMessage("Enter port for chat's server please");
            int port = ConsoleHelper.readInt();
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

        @Override
        public void run()
        {
            String userName = null;
            ConsoleHelper.writeMessage(("Just connected to " + socket.getRemoteSocketAddress()));
            try (Connection connection = new Connection(socket))
            {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
                ConsoleHelper.writeMessage("Connection with remote address has been closed");
            }
            catch (IOException | ClassNotFoundException e)
            {
                ConsoleHelper.writeMessage("Remote address connection error");
                if (userName != null && !userName.equals(""))
                {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
        {
            String name;
            while (true)
            {
                connection.send(new Message(MessageType.NAME_REQUEST));

                Message message = connection.receive();
                if (message.getType().equals(MessageType.USER_NAME))
                {
                    name = message.getData();
                    if (name != null && !name.equals("") && !connectionMap.containsKey(name))
                    {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }
                }
            }
            return name;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException
        {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet())
            {
                String oldUserName = entry.getKey();
                if (!userName.equals(oldUserName))
                {
                    Message message = new Message(MessageType.USER_ADDED, oldUserName);
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message message = connection.receive();
                if (message.getType().equals(MessageType.TEXT))
                {
                    String textMessage = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, textMessage));
                } else
                {
                    ConsoleHelper.writeMessage("Message type error");
                }
            }
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
