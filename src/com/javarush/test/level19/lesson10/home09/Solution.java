package com.javarush.test.level19.lesson10.home09;

/* Контекстная реклама
В методе main подмените объект System.out написанной вами реадер-оберткой
Ваша реадер-обертка должна выводить на консоль контекстную рекламу после каждого второго println-а
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Рекламный текст: "JavaRush - курсы Java онлайн"

Пример вывода:
first
second
JavaRush - курсы Java онлайн
third
fourth
JavaRush - курсы Java онлайн
fifth
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        PrintStream defOut = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrintStream stream = new PrintStream(baos);

        System.setOut(stream);

        testString.printSomething();

        String[] s = baos.toString().split(System.lineSeparator());

        int k = 1;
        String result = "";
        for (int i = 0; i < s.length; i++) {
            if (k == 3) {
                result += "JavaRush - курсы Java онлайн\n" + s[i] + "\n";
                k = 1;
            } else {
                result += s[i] + "\n";
            }
            k++;
            if ((i == s.length - 1) && (k == 3)) {
                result += "JavaRush - курсы Java онлайн\n";
            }
        }

        System.setOut(defOut);

        System.out.println(result);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
