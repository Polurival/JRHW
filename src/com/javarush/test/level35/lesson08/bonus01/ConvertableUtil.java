package com.javarush.test.level35.lesson08.bonus01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static Map convert(List<? extends Convertable> list) {
        Map result = new HashMap<>();
        for (Convertable element : list)
        {
            result.put(element.getKey(), element);
        }
        return result;
    }
}
