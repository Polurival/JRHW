package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human). Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Human hgf1 = new Human("дедушка Валера", true, 67, null, null);
        Human hgf2 = new Human("дедушка Афонасий", true, 70, null, null);

        Human hgm1 = new Human("бабушка Галя", false, 56, null, null);
        Human hgm2 = new Human("бабушка Полина", false, 65, null, null);

        Human hf1 = new Human("отец Юра", true, 30, hgf1, hgm1);
        Human hm1 = new Human("мама Маша", false, 26, hgf2, hgm2);

        Human hc1 = new Human("Саша", true, 3, hf1, hm1);
        Human hc2 = new Human("Света", false, 2, hf1, hm1);
        Human hc3 = new Human("Виктор", true, 1, hf1, hm1);

        System.out.println(hgf1.toString());
        System.out.println(hgf2.toString());
        System.out.println(hgm1.toString());
        System.out.println(hgm2.toString());
        System.out.println(hf1.toString());
        System.out.println(hm1.toString());
        System.out.println(hc1.toString());
        System.out.println(hc2.toString());
        System.out.println(hc3.toString());

    }

    public static class Human
    {
        //напишите тут ваш код
        String name;
        boolean sex;
        int age;
        Human father;
        Human mother;

        public Human(String name, boolean sex, int age, Human father, Human mother)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}
