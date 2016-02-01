package com.javarush.test.level20.lesson10.home02;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуйте объект в методе getOriginalObject предварительно определив, какого именно типа там объект.
Реализуйте интерфейс Serializable где необходимо.
*/
public class Solution implements Serializable {
    public A getOriginalObject(ObjectInputStream objectStream) {
        A a;
        B b;
        Object object = null;
        try
        {
            object = objectStream.readObject();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        if (object instanceof B) {
            b = (B) object;
            return b;
        } else {
            a = (A) object;
            return a;
        }
    }

    public class A implements Serializable {
        private static final long serialVersionUID = 1L;
    }

    public class B extends A {
        private static final long serialVersionUID = 2L;
        public B() {
            System.out.println("inside B");
        }
    }
}
