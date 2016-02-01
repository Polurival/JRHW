package com.javarush.test.level15.lesson09.task01;

import java.util.HashMap;
import java.util.Map;

/* Статики 1
В статическом блоке инициализировать labels 5 различными парами.
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static
    {
        labels.put(5.0, "Lg");
        labels.put(6.0, "L7");
        labels.put(78.0, "Lio");
        labels.put(3.0, "Lui");
        labels.put(1.0, "Lfh");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
