package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        //напишите тут ваши переменные и конструкторы
        private String name;
        private boolean sex;
        private int age;
        private int height;
        private int weight;
        private int equipment;

        public Human(String name) // 1
        {
            this.name = name;
        }
        public Human(String name, boolean sex) // 2
        {
            this.name = name;
            this.sex = sex;
        }
        public Human(String name, boolean sex, int age) // 3
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }
        public Human(String name, boolean sex, int age, int height) // 4
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.height = height;
        }
        public Human(String name, boolean sex, int age, int height, int weight) // 5
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.height = height;
            this.weight = weight;
        }
        public Human(String name, boolean sex, int age, int height, int weight, int equipment) // 6
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.height = height;
            this.weight = weight;
            this.equipment = equipment;
        }
        public Human(boolean sex, int age, int height, int weight, int equipment) // 7
        {
            this.sex = sex;
            this.age = age;
            this.height = height;
            this.weight = weight;
            this.equipment = equipment;
        }
        public Human(int age, int height, int weight, int equipment) // 8
        {
            this.age = age;
            this.height = height;
            this.weight = weight;
            this.equipment = equipment;
        }
        public Human(int height, int weight, int equipment) // 9
        {
            this.height = height;
            this.weight = weight;
            this.equipment = equipment;
        }
        public Human(int height, int equipment) // 10
        {
            this.height = height;
            this.equipment = equipment;
        }
    }
}
