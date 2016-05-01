package com.javarush.test.level40.lesson08.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

/* Отправка GET-запроса через сокет
Перепиши реализацию метода getSite, он должен явно создавать и использовать сокетное соединение Socket с сервером.
Адрес сервера и параметры для GET-запроса получи из параметра url.
Порт используй дефолтный для http.
Классы HttpURLConnection, HttpClient и т.д. не использовать.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            String host = url.getHost();
            int port = url.getDefaultPort();
            String path = url.getPath();

            Socket clientSocket = new Socket(host, port);

            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
            pw.println("GET " + path + " HTTP/1.1");
            pw.println("Host: " + host);
            pw.println("User-Agent: Mozilla/5.0");
            pw.println("Accept: text/html");
            pw.println("Connection: close");
            pw.println("");
            pw.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}