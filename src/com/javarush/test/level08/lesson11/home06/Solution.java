package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;
import java.util.Collections;

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Human ch1 = new Human("Маша", false, 13, new ArrayList<Human>());
        Human ch2 = new Human("Миша", true, 9, new ArrayList<Human>());
        Human ch3 = new Human("Алена", false, 11, new ArrayList<Human>());
        ArrayList<Human> list1 = new ArrayList<Human>();
        Collections.addAll(list1, ch1, ch2, ch3);

        Human m1 = new Human("мама Полина", false, 30, list1);
        Human f1 = new Human("папа Василий", true, 35, list1);
        ArrayList<Human> list2 = new ArrayList<Human>();
        ArrayList<Human> list3 = new ArrayList<Human>();
        list2.add(m1);
        list3.add(f1);

        Human gm1 = new Human("бабушка Вера", false, 65, list2);
        Human gf1 = new Human("дедушка Борис", true, 70, list2);

        Human gm2 = new Human("бабушка Лариса", false, 63, list3);
        Human gf2 = new Human("дедушка Прокофий", true, 68, list3);

        System.out.println(gf1);
        System.out.println(gf2);

        System.out.println(gm1);
        System.out.println(gm2);

        System.out.println(f1);
        System.out.println(m1);

        System.out.println(ch1);
        System.out.println(ch2);
        System.out.println(ch3);

    }

    public static class Human
    {
        //напишите тут ваш код
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children;

        public Human(String name, boolean sex, int age, ArrayList<Human> children)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }

            return text;
        }
    }

}
