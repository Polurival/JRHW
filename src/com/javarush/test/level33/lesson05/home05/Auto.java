package com.javarush.test.level33.lesson05.home05;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property="className")
@JsonSubTypes({
        @JsonSubTypes.Type(value=Car.class, name="com.javarush.test.level33.lesson05.home05.Car"),
        @JsonSubTypes.Type(value=Motorbike.class, name="com.javarush.test.level33.lesson05.home05.Motorbike"),
        @JsonSubTypes.Type(value=RaceBike.class, name="com.javarush.test.level33.lesson05.home05.RaceBike")
})
public abstract class Auto {
    protected String name;
    protected String owner;
    protected int age;
}