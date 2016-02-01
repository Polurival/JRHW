package com.javarush.test.level22.lesson13.task02;

import java.io.*;
import java.nio.charset.Charset;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution
{
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException
    {

        FileInputStream fis = new FileInputStream(args[0]);
        byte[] buff_fis = new byte[fis.available()];
        fis.read(buff_fis);
        fis.close();

        String s = new String(buff_fis, "UTF-8"); // переводим байты из windows в utf

        FileOutputStream fos = new FileOutputStream(args[1]);
        fos.write(s.getBytes("windows-1251")); // записываем байты из utf в windows
        fos.close();

    }
}
