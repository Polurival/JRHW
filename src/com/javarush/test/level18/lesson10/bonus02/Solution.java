package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();

        if ("-c".equals(args[0]))
        {
            // подготовка id и запись в файл
            FileInputStream fis = new FileInputStream(file);
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(fis));
            String lastProduct = "";
            while (true) {
                String temp = bufReader.readLine();
                if (temp == null) break;
                lastProduct = temp;

            }
            int id;
            String idStr = "";
            if ("".equals(lastProduct)) id = 0;
            else {
                idStr = lastProduct.substring(0, 8);
                id = Integer.parseInt(idStr.trim());
            }
            id++;
            idStr = String.valueOf(id);
            if (idStr.length() > 8) {
                idStr = idStr.substring(0, 8);
            }
            else {
                int idLength = idStr.length();
                for (int i = 0; i < (8 - idLength); i++)
                {
                    idStr += " ";
                }
            }
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write("\r\n".getBytes());
            fos.write(idStr.getBytes());
            //----------

            // подготовка productName и запись в файл
            String prodNameStr = "";
            for (int i = 1; i < (args.length - 2); i++) {
                prodNameStr += (args[i] + " ");
            }
            if (prodNameStr.length() > 30)
            {
                prodNameStr = prodNameStr.substring(0, 30);
            }
            else
            {
                int prodNameLength = prodNameStr.length();
                for (int i = 0; i < (30 - prodNameLength); i++) {
                    prodNameStr += " ";
                }
            }
            fos.write(prodNameStr.getBytes());
            //---------

            // подготовка price и запись в файл
            String priceStr = args[args.length - 2];
            if (priceStr.length() > 8) {
                priceStr = priceStr.substring(0, 8);
            }
            else
            {
                int priceLength = priceStr.length();
                for (int i = 0; i < (8 - priceLength); i++) {
                    priceStr += " ";
                }
            }
            fos.write(priceStr.getBytes());
            //---------

            // подготовка quantity и запись в файл
            String quantityStr = args[args.length - 1];
            if (quantityStr.length() > 4) {
                quantityStr = quantityStr.substring(0, 4);
            }
            else
            {
                int quantityLength = quantityStr.length();
                for (int i = 0; i < (4 - quantityLength); i++) {
                    quantityStr += " ";
                }
            }
            fos.write(quantityStr.getBytes());
            //---------

            fis.close();
            bufReader.close();
            fos.close();
        }
        reader.close();
    }
}
