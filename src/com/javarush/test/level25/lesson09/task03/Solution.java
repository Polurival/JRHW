package com.javarush.test.level25.lesson09.task03;

/* Живем своим умом
В классе Solution реализуйте интерфейс UncaughtExceptionHandler, который должен:
1. прервать нить, которая бросила исключение.
2. вывести в консоль стек исключений начиная с самого вложенного.
Пример исключения: new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))
Пример вывода:
java.lang.IllegalAccessException: GHI
java.lang.RuntimeException: DEF
java.lang.Exception: ABC
*/
public class Solution implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        if (t != null)
        {
            t.interrupt();
        }

        Throwable eCause = e.getCause();
        if (eCause != null) {
            uncaughtException(t, eCause);
        }
        System.out.println(e.getClass().getName() + ": " + e.getMessage());

    }

    public static void main(String[] args)
    {
        Throwable e = new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
        new Solution().uncaughtException(Thread.currentThread(), e);
    }
}
