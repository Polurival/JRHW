package com.javarush.test.level17.lesson10.bonus01;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        if (args[0].equals("-c")) {
            Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3]);
            if (args[2].equals("ж")) {
                Person person = Person.createFemale(args[1], date);
                allPeople.add(person);
                System.out.println(allPeople.indexOf(person));
            }
            else if (args[2].equals("м")) {
                Person person = Person.createMale(args[1], date);
                allPeople.add(person);
                System.out.println(allPeople.indexOf(person));
            }
        }
        else if (args[0].equals("-u")) {
            Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[4]);
            if (args[3].equals("ж")) {
                allPeople.set(Integer.parseInt(args[1]), Person.createFemale(args[2], date));
            }
            else if (args[3].equals("м")) {
                allPeople.set(Integer.parseInt(args[1]), Person.createMale(args[2], date));
            }
        }
        else if (args[0].equals("-d")) {
            Person person = allPeople.get(Integer.parseInt(args[1]));
            person.setName(null);
            person.setSex(null);
            person.setBirthDay(null);
        }
        else if (args[0].equals("-i")) {
            Person person = allPeople.get(Integer.parseInt(args[1]));
            String s = null;

            if (person.getSex().equals(Sex.FEMALE)) {
                s = "ж";
            }
            else if (person.getSex().equals(Sex.MALE)) {
                s = "м";
            }
            String date = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(person.getBirthDay());

            System.out.println(person.getName() + " " + " " + s + " " + date);
        }
    }
}

