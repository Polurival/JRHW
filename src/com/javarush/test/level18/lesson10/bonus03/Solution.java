package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        HashMap<Integer, String> hm = new HashMap<Integer, String>();
        ArrayList<Integer> idList = new ArrayList<Integer>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        // заполнение HashMap и Arraylist для перезаписи файла
        FileInputStream fis = new FileInputStream(file);
        BufferedReader bufis = new BufferedReader(new InputStreamReader(fis));
        while (true) {
            String s = bufis.readLine();
            if (s == null) break;
            String idStr = s.substring(0, 8).trim();
            int id = Integer.parseInt(idStr);
            idList.add(id);
            hm.put(id, s);
        }
        fis.close();
        bufis.close();
        FileOutputStream fos = new FileOutputStream(file);
        // -------

        if ("-u".equals(args[0])) {

            // подготовка id для обновления или удаления
            String uIdStr = args[1];
            if (uIdStr.length() > 8) {
                uIdStr = uIdStr.substring(0, 8);
            } else {
                int uIdLength = uIdStr.length();
                for (int i = 0; i < (8 - uIdLength); i++) {
                    uIdStr += " ";
                }
            }
            int uId = Integer.parseInt(uIdStr.trim()); // для обновления по id
            // -------

            // подготовка productName для обновления
            String uProductNameStr = "";
            for (int i = 2; i < args.length - 2; i++) {
                uProductNameStr += (args[i] + " ");
            }
            if (uProductNameStr.length() > 30) {
                uProductNameStr = uProductNameStr.substring(0, 30);
            } else {
                int uProductNameLength = uProductNameStr.length();
                for (int i = 0; i < (30 - uProductNameLength); i++) {
                    uProductNameStr += " ";
                }
            }
            // -------

            // подготовка price для обновления
            String uPriceStr = args[args.length - 2];
            if (uPriceStr.length() > 8) {
                uPriceStr = uPriceStr.substring(0, 8);
            } else {
                int uPriceLength = uPriceStr.length();
                for (int i = 0; i < (8 - uPriceLength); i++) {
                    uPriceStr += " ";
                }
            }
            // -------

            // подготовка quantity для обновления
            String uQuantityStr = args[args.length - 1];
            if (uQuantityStr.length() > 4) {
                uQuantityStr = uQuantityStr.substring(0, 4);
            } else {
                int uQuantityLength = uQuantityStr.length();
                for (int i = 0; i < (4 - uQuantityLength); i++) {
                    uQuantityStr += " ";
                }
            }
            // -------

            // обновление строчки по uId
            String uResult = uIdStr + uProductNameStr + uPriceStr + uQuantityStr;
            hm.remove(uId);
            hm.put(uId, uResult);

            for (int i = 0; i < idList.size(); i++) {
                fos.write(hm.get(idList.get(i)).getBytes());
                fos.write("\r\n".getBytes());
            }
            // -------


        } else if ("-d".equals(args[0])) {
            int dId = Integer.parseInt(args[1]);
            idList.remove(idList.indexOf(dId));
            for (int i = 0; i < idList.size(); i++) {
                fos.write(hm.get(idList.get(i)).getBytes());
                fos.write("\r\n".getBytes());
            }
        }
        fos.close();

    }
}
