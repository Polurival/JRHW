package com.javarush.test.level30.lesson02.task01;

/* Осваиваем методы класса Integer
Используя метод Integer.parseInt(String, int) реализуйте логику метода convertToDecimalSystem,
который должен переводить переданную строку в десятичное число и возвращать его в виде строки.
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        String number;
        int base;
        if (s.charAt(0) == '0') {
            if (s.charAt(1) == 'x') {
                number = s.substring(2);
                base = 16;
            } else
            if (s.charAt(1) == 'b') {
                number = s.substring(2);
                base = 2;
            } else {
                number = s.substring(1);
                base = 8;
            }
        } else {
            number = s;
            base = 10;
        }

        return String.valueOf(Integer.parseInt(number, base));
    }
}
