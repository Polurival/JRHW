package com.javarush.test.level37.lesson04.big01;

import com.javarush.test.level37.lesson04.big01.female.FemaleFactory;
import com.javarush.test.level37.lesson04.big01.male.MaleFactory;

/**
 * Created by
 * Polurival on 26.04.2016.
 */
public class FactoryProducer
{
    public static enum HumanFactoryType
    {
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory(HumanFactoryType humanFactoryType)
    {
        return humanFactoryType == HumanFactoryType.MALE ? new MaleFactory() : new FemaleFactory();
    }
}
