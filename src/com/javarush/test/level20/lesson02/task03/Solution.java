package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution
{
    public static Map<String, String> properties = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.fillInPropertiesMap();

        for(Map.Entry<String,String> entry : properties.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "=" + value);
        }

        File file = new File("./2.properties");
        FileOutputStream fos = new FileOutputStream(file);
        solution.save(fos);

    }

    public void fillInPropertiesMap()
    {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        try
        {
            fileName = reader.readLine();
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            load(fis);
            fis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void save(OutputStream outputStream) throws Exception
    {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        for (Map.Entry<String, String> entry : properties.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();
            prop.setProperty(key, value);
        }
        prop.store(outputStream, null);
        outputStream.close();
    }

    public void load(InputStream inputStream) throws Exception
    {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        prop.load(inputStream);
        Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            properties.put(key,value);
        }
        inputStream.close();
    }
}
