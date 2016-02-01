package com.javarush.test.level14.lesson06.home01;

/**
 * Created by PolschikovYV on 29.06.15.
 */
public class RussianHen extends Hen
{
    @Override
    int getCountOfEggsPerMonth()
    {
        return 100500;
    }

    @Override
    String getDescription()
    {
        return super.getDescription() + " Моя страна - " + Country.RUSSIA + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
