package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        PrintStream defaultOut = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrintStream stream = new PrintStream(baos);

        System.setOut(stream);

        testString.printSomething();

        String s = baos.toString();
        String[] arrStr = s.split(" ");
        int arg1 = Integer.parseInt(arrStr[0]);
        String oper = arrStr[1];
        int arg2 = Integer.parseInt(arrStr[2]);
        int result = 0;
        if ("+".equals(oper)) {
            result = arg1 + arg2;
        } else if ("-".equals(oper)) {
            result = arg1 - arg2;
        } else if ("*".equals(oper)) {
            result = arg1 * arg2;
        }
        s = arg1 + " " + oper + " " + arg2 + " = " + result;

        System.setOut(defaultOut);

        System.out.println(s);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

