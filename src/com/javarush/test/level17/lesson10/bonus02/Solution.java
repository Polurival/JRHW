package com.javarush.test.level17.lesson10.bonus02;

import com.javarush.test.level17.lesson10.bonus01.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException
    {
        //start here - начни тут
        if (args[0].equals("-c")) {
            for (int i = 1; i < args.length - 2; i = i + 3)
            {
                Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i + 2]);
                if (args[i + 1].equals("ж"))
                {
                    Person person = Person.createFemale(args[i], date);
                    allPeople.add(person);
                    System.out.println(allPeople.indexOf(person));
                } else if (args[i + 1].equals("м"))
                {
                    Person person = Person.createMale(args[i], date);
                    allPeople.add(person);
                    System.out.println(allPeople.indexOf(person));
                }
            }
        }
        else if (args[0].equals("-u")) {
            for (int i = 1; i < args.length - 3; i = i + 4)
            {
                Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i + 3]);
                if (args[i + 2].equals("ж"))
                {
                    allPeople.set(Integer.parseInt(args[i]), Person.createFemale(args[i + 1], date));
                } else if (args[i + 2].equals("м"))
                {
                    allPeople.set(Integer.parseInt(args[i]), Person.createMale(args[i + 1], date));
                }
            }
        }
        else if (args[0].equals("-d")) {
            for (int i = 1; i < args.length; i++)
            {
                Person person = allPeople.get(Integer.parseInt(args[i]));
                person.setName(null);
                person.setSex(null);
                person.setBirthDay(null);
            }
        }
        else if (args[0].equals("-i")) {
            for (int i = 1; i < args.length; i++)
            {
                Person person = allPeople.get(Integer.parseInt(args[i]));
                String s = null;

                if (person.getSex().equals(Sex.FEMALE))
                {
                    s = "ж";
                } else if (person.getSex().equals(Sex.MALE))
                {
                    s = "м";
                }
                String date = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(person.getBirthDay());

                System.out.println(person.getName() + " " + " " + s + " " + date);
            }
        }
    }
}
