package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream(args[0]);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        while (br.ready()) {
            String[] s = br.readLine().split(" ");
            String name = "";
            for (int i = 0; i < s.length - 3; i++) {
                if (i == s.length - 4)
                    name += s[i];
                else
                    name += s[i] + " ";
            }
            int day = Integer.parseInt(s[s.length - 3]);
            int month = Integer.parseInt(s[s.length - 2]) - 1;
            int year = Integer.parseInt(s[s.length - 1]) - 1900;
            Date bDate = new Date(year, month, day);

            PEOPLE.add(new Person(name, bDate));
        }

        for (Person p : PEOPLE) {
            System.out.println(p.getName() + " " + p.getBirthday());
        }

        fis.close();
        br.close();

    }
}
