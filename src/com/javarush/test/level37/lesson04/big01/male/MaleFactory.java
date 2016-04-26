package com.javarush.test.level37.lesson04.big01.male;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by
 * Polurival on 26.04.2016.
 */
public class MaleFactory implements AbstractFactory
{
    @Override
    public Human getPerson(int age)
    {
        if (age <= KidBoy.MAX_AGE)
        {
            return new KidBoy();
        } else if (age > KidBoy.MAX_AGE && age <= TeenBoy.MAX_AGE)
        {
            return new TeenBoy();
        } else
        {
            return new Man();
        }
    }
}
