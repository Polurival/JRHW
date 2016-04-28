package com.javarush.test.level38.lesson04.task02;

/* Непроверяемые исключения (unchecked exception)
Напиши реализацию метода methodThrowsClassCastException(). Он должен
всегда кидать непроверяемое исключение ClassCastException.

Напиши реализацию метода methodThrowsNullPointerException(). Он должен
всегда кидать непроверяемое исключение NullPointerException.

Кинуть исключение (throw) явно нельзя.
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object obj = new Object();
        Object objArr[] = (Object[]) obj;
        //напишите тут ваш код
    }

    public void methodThrowsNullPointerException() {
        //напишите тут ваш код
        Integer i = null;
        int g = 1 + i;
    }
}
