package com.javarush.test.level32.lesson08.bonus01;

import com.javarush.test.level32.lesson08.bonus01.Big;
import com.javarush.test.level32.lesson08.bonus01.Item;
import com.javarush.test.level32.lesson08.bonus01.Small;

import java.lang.reflect.Proxy;
import java.util.*;

/* Дженерики для создания прокси-объекта
В классе Solution создайте публичный метод getProxy
1) Метод getProxy должен возвращать прокси для любого интерфейса, который наследуется от Item
2) getProxy должен иметь два параметра. Первый - класс возвращаемого типа, второй - классы дополнительных интерфейсов.
3) Используйте ItemInvocationHandler для создания прокси
Метод main не участвует в тестировании
*/
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.getProxy(Item.class));                        //true false false
        test(solution.getProxy(Item.class, Small.class));           //true false true
        test(solution.getProxy(Item.class, Big.class, Small.class));//true true true
        test(solution.getProxy(Big.class, Small.class));            //true true true т.к. Big наследуется от Item
        test(solution.getProxy(Big.class));                         //true true false т.к. Big наследуется от Item
    }


    private static void test(Object proxy) {
        boolean isItem = proxy instanceof Item;
        boolean isBig = proxy instanceof Big;
        boolean isSmall = proxy instanceof Small;

        System.out.format("%b %b %b\n", isItem, isBig, isSmall);
    }

    public <T extends Item> T getProxy(Class<T> item, Class<?>... items) {
        ClassLoader classLoader = item.getClassLoader();

        Class<T>[] interfaces = null;
        if (item.getSimpleName().equals("Item")) {
            interfaces = new Class[]{item};
        } else if (item.getInterfaces() != null) {
            interfaces = new Class[item.getInterfaces().length];
            for (int i = 0; i < item.getInterfaces().length; i++) {
                if (item.getInterfaces()[i].getSimpleName().equals("Item"))
                {
                    interfaces[i] = item;
                }
            }
        }

        Class<T>[] implInterfaces = (Class<T>[]) item.getInterfaces();

        Class<T>[] additionInterfaces = new Class[items.length];
        for (int i = 0; i < items.length; i++)
        {
            additionInterfaces[i] = (Class<T>) items[i];
        }

        Collection<Class<T>> allInterfacesCollection = new ArrayList<>();
        if (interfaces != null)
        {
            allInterfacesCollection.addAll(Arrays.asList(interfaces));
        }
        if (implInterfaces != null)
        {
            allInterfacesCollection.addAll(Arrays.asList(implInterfaces));
        }
        allInterfacesCollection.addAll(Arrays.asList(additionInterfaces));

        Class<?>[] allInterfaces = allInterfacesCollection.toArray(new Class[allInterfacesCollection.size()]);

        Object proxy = Proxy.newProxyInstance(classLoader, allInterfaces, new ItemInvocationHandler());
        return (T) proxy;
    }
}
