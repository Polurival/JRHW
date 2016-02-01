package com.javarush.test.level05.lesson09.task03;

/* Создать класс Dog
Создать класс Dog (собака) с тремя конструкторами:
- Имя
- Имя, рост
- Имя, рост, цвет
*/

public class Dog
{
    //Напишите тут ваш код
    private String name, color;
    private int height;

    public Dog(String name) {
        this.name = name;
    }
    public Dog(String name, int height) {
        this.name = name;
        this.height = height;
    }
    public Dog(String name, int height, String color) {
        this.name = name;
        this.height = height;
        this.color = color;
    }

}
