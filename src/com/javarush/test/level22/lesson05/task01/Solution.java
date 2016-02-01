package com.javarush.test.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
*/

public class Solution
{
    public static String getPartOfString(String string) throws TooShortStringException
    {
        if (string == null) throw new TooShortStringException();
        if (string.isEmpty()) throw new TooShortStringException();
        int index = 0;
        int spaceNumber = 0;
        int firstIndex = 0;
        int fourthIndex = 0;
        int fifthIndex = 0;
        while (spaceNumber < 5 || index != -1)
        {
            index = string.indexOf(' ', index + 1);
            spaceNumber++;
            if (spaceNumber == 1) firstIndex = index;//first space index
            if (spaceNumber == 4) fourthIndex = index;//fourth space index
            if (spaceNumber == 5) fifthIndex = index;//fifth space index
        }
        if (fourthIndex <= 0)
            throw new TooShortStringException();// if amount of spaces is less then 4 then throw an exception
        if (fourthIndex > 0 && fifthIndex <= 0)
        {//if there are only 4 spaces
            if ((fourthIndex + 1) == string.length())
                throw new TooShortStringException();//if the last character of the string is the fourth space, then throw an exception
            else if (!Character.isLetter(string.charAt(fourthIndex + 1))) throw new TooShortStringException();
            return string.substring(++firstIndex);
        }
        if (fifthIndex > 0)
        {
            if (!Character.isLetter(string.charAt(fourthIndex + 1))) throw new TooShortStringException();
            if (fifthIndex - fourthIndex == 1)
                return string.substring(++firstIndex, ++fifthIndex);//if there are two spaces in the row
            return string.substring(++firstIndex, fifthIndex);
        }
        throw new TooShortStringException();
    }

    public static class TooShortStringException extends Exception
    {
    }

    public static void main(String[] args) throws TooShortStringException
    {
        System.out.println(getPartOfString("JavaRush — лучшийсервисобучения Java. . "));
    }
}


// Мое решение, которое не принималось:
/*public class Solution
{
    public static String getPartOfString(String string) throws TooShortStringException
    {
        ArrayList<Integer> gapIndexList;
        String temp = "";

        if (string == null)
        {
            throw new TooShortStringException();
        }
        gapIndexList = new ArrayList<>();
        for (int i = 0; i < string.length(); i++)
        {
            if (' ' == string.charAt(i))
            {
                gapIndexList.add(i);
            }
        }

        for (int i = 0; i < gapIndexList.size() - 1; i++)
        {
            if ((gapIndexList.get(i) + 1) == gapIndexList.get(i + 1))
            {
                throw new TooShortStringException();
            }
        }

        *//*if ((gapIndexList.size() < 5) || (gapIndexList.get(4) == string.length() - 1))
        {
            throw new TooShortStringException();
        }*//*


        if (gapIndexList.size() == 4)
        {
            temp = string.substring(gapIndexList.get(0) + 1);
        } else
        {
            temp = string.substring(gapIndexList.get(0) + 1, gapIndexList.get(4));
        }


        return temp;
    }

    public static class TooShortStringException extends Exception
    {
    }


    public static void main(String[] args) throws TooShortStringException
    {
        System.out.println(getPartOfString("nfnf nfnf nfnf nfnf    "));
    }
}*/
