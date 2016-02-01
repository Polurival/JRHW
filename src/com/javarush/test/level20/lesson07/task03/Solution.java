package com.javarush.test.level20.lesson07.task03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Externalizable Person
Класс Person должен сериализоваться с помощью интерфейса Externalizable.
Подумайте, какие поля не нужно сериализовать.
Исправьте ошибку сериализации.
Сигнатуры методов менять нельзя.
*/
public class Solution
{

    public static void main(String[] args)
    {
        Person personf = new Person("Вася", "Пупкин", 28);
        Person personm = new Person("Ника", "Кар", 26);
        Person child1 = new Person("Валя", "Пупкина", 10);
        Person child2 = new Person("Галя", "Пупкина", 9);
        child1.setMother(personm);
        child1.setFather(personf);
        child2.setMother(personm);
        child2.setFather(personf);
        ArrayList<Person> list = new ArrayList<>();
        list.add(child1);
        list.add(child2);
        personf.setChildren(list);
        personm.setChildren(list);

        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./1.txt"));
            oos.writeObject(personm);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./1.txt"));
            Object o = ois.readObject();
            Person newPerson = (Person) o;
            System.out.println(newPerson.toString());
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

    public static class Person implements Externalizable
    {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person()
        {
        }

        public Person(String firstName, String lastName, int age)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother)
        {
            this.mother = mother;
        }

        public void setFather(Person father)
        {
            this.father = father;
        }

        public void setChildren(List<Person> children)
        {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException
        {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeObject(age);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
        {
            firstName = (String) in.readObject();
            lastName = (String) in.readObject();
            age = (Integer) in.readObject();
            mother = (Person) in.readObject();
            father = (Person) in.readObject();
            children = (List<Person>) in.readObject();

        }

        @Override
        public String toString()
        {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
