package com.javarush.test.level08.lesson03.task01;

/* HashSet из растений
Создать коллекцию HashSet с типом элементов String.
Добавить в неё 10 строк: арбуз, банан, вишня, груша, дыня, ежевика, жень-шень, земляника, ирис, картофель.
Вывести содержимое коллекции на экран, каждый элемент с новой строки.
Посмотреть, как изменился порядок добавленных элементов.
*/

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        Set<String> set = new HashSet<String>();
        Collections.addAll(set, "арбуз", "банан", "вишня", "груша", "дыня", "ежевика", "жень-шень",
                "земляника", "ирис", "картофель");

        for (String aSet : set) System.out.println(aSet);

    }
}
