package com.javarush.test.level14.lesson06.home01;

/**
 * Created by PolschikovYV on 29.06.15.
 */
public class BelarusianHen extends Hen
{
    @Override
    int getCountOfEggsPerMonth()
    {
        return 5000;
    }

    @Override
    String getDescription()
    {
        return super.getDescription() + " Моя страна - " + Country.BELARUS + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
