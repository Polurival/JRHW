package com.javarush.test.level04.lesson10.task04;

/* S-квадрат
Вывести на экран квадрат из 10х10 букв S используя цикл while.
Буквы в одной строке не разделять.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        int x = 1;
        int y = 1;
        while (y <= 10){
            y++;
            while (x <= 10) {
                x++;
                System.out.print("S");
            }
            x = 1;
            System.out.println();
        }

    }
}
