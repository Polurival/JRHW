package com.javarush.test.level20.lesson10.home03;

import java.io.*;

/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
Метод main не участвует в тестировании.
*/
public class Solution implements Serializable
{
    public static class A
    {
        protected String name = "A";

        public A() {

        }

        public A(String name)
        {
            this.name += name;
        }
    }

    public class B extends A implements Serializable
    {
        public B(String name)
        {
            super(name);
            this.name += name;
        }
        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            name = (String)s.readObject();
        }
        private void writeObject(ObjectOutputStream s) throws IOException {
            s.writeObject(this.name);
        }
    }

    public static void main(String[] args)
    {
        Solution sol = new Solution();
        B b = sol.new B("b");
        System.out.println(b.name);
        B newB;
        try
               {
                   ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./1.txt"));
                   ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./1.txt"));

                   oos.writeObject(b);
                   newB = (B) ois.readObject();

                   oos.close();
                   ois.close();

                   System.out.println(newB.name);
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
}
