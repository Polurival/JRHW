package com.javarush.test.level20.lesson10.home05;

import java.io.*;
import java.util.logging.Logger;

/* Сериализуйте Person
Сериализуйте класс Person стандартным способом. При необходимости поставьте полям модификатор transient.
*/
public class Solution {

    public static void main(String[] args)
    {
        Person p = new Person("Иван", "Иванов", "Россия", Sex.MALE);
        System.out.println(p);
        Person newPerson;
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./1.txt"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./1.txt"));

            oos.writeObject(p);
            newPerson = (Person) ois.readObject();
            System.out.println(p);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greetingString;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greetingString = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }

        @Override
        public String toString()
        {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", fullName='" + fullName + '\'' +
                    ", greetingString='" + greetingString + '\'' +
                    ", country='" + country + '\'' +
                    ", sex=" + sex +
                    ", outputStream=" + outputStream +
                    '}';
        }
    }

    enum Sex {
        MALE,
        FEMALE
    }
}
