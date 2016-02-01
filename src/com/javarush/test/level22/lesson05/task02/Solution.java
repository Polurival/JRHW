package com.javarush.test.level22.lesson05.task02;

/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException
    {
        String temp;
        try {
            int firstIndexOfTab = string.indexOf("\t");
            int secondIndexOfTab = string.indexOf("\t", firstIndexOfTab + 1);
            temp = string.substring(firstIndexOfTab + 1, secondIndexOfTab);
        } catch (Exception e) {
            throw new TooShortStringException();
        }
        return temp;
    }

    public static class TooShortStringException extends Exception {
    }
}
