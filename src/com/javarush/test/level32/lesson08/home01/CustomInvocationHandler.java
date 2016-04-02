package com.javarush.test.level32.lesson08.home01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by
 * Polurival on 02.04.2016.
 */
public class CustomInvocationHandler implements InvocationHandler
{
    private SomeInterfaceWithMethods someInterfaceWithMethods;

    public CustomInvocationHandler(SomeInterfaceWithMethods someInterfaceWithMethods)
    {
        this.someInterfaceWithMethods = someInterfaceWithMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Object obj = null;
        switch (method.getName())
        {
            case "voidMethodWithoutArgs":
                System.out.println("voidMethodWithoutArgs in");
                obj = method.invoke(someInterfaceWithMethods, args);
                System.out.println("voidMethodWithoutArgs out");
                break;
            case "stringMethodWithoutArgs":
                System.out.println("stringMethodWithoutArgs in");
                obj = method.invoke(someInterfaceWithMethods, args);
                System.out.println("stringMethodWithoutArgs out");
                break;
            case "voidMethodWithIntArg":
                System.out.println("voidMethodWithIntArg in");
                obj = method.invoke(someInterfaceWithMethods, args);
                System.out.println("voidMethodWithIntArg out");
                break;
        }
        return obj;
    }
}
