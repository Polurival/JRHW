package com.javarush.test.level05.lesson12.home03;

/* Создай классы Dog, Cat, Mouse
Создай классы Dog, Cat, Mouse. Добавь по три поля в каждый класс, на твой выбор. Создай объекты для героев мультика Том и Джерри. Так много, как только вспомнишь.
Пример:
Mouse jerryMouse = new Mouse(“Jerry”, 12 , 5), где 12 - высота в см, 5 - длина хвоста в см.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Mouse jerryMouse = new Mouse("Jerry", 12, 5);

        //Напишите тут ваш код
        Cat tomCat = new Cat("Tom", "backyard", 100500);
        Cat butchCat = new Cat("Butch", "backyard", 500);
        Dog spikeDog = new Dog("Spike", 10, 100500);
        Dog tikeDog = new Dog("Tike", 1, 100);
    }

    public static class Mouse
    {
        String name;
        int height;
        int tail;

        public Mouse(String name, int height, int tail)
        {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }
    }

    //Напишите тут ваши классы
    public static class Dog
    {
        String name;
        int age;
        int strength;

        public Dog(String name, int age, int strength)
        {
            this.name = name;
            this.age = age;
            this.strength = strength;
        }
    }

    public static class Cat
    {
        String name;
        String breed;
        int trick;

        public Cat(String name, String breed, int trick)
        {
            this.name = name;
            this.breed = breed;
            this.trick = trick;
        }
    }

}
