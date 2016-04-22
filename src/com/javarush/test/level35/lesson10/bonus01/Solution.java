package com.javarush.test.level35.lesson10.bonus01;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution
{
    public static void main(String[] args)
    {
        Set<? extends Animal> allAnimals = getAllAnimals("C://pathToClasses/");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals)
    {
        Set<Animal> set = new HashSet<>();

        String[] files = new File(pathToAnimals).list();

        for (String file : files)
        {
            boolean hasAnimalInterface = false;
            boolean hasDefaultConstructor = false;

            try
            {
                ClassLoader classLoader = ClassLoader.getSystemClassLoader();
                String clazzName = file.split(".class")[0];
                String packaje = Animal.class.getPackage().getName();
                Class clazz = classLoader.loadClass(packaje + ".data." + clazzName);

                for (Class interfaze : clazz.getInterfaces())
                {
                    if ("Animal".equals(interfaze.getSimpleName()))
                    {
                        hasAnimalInterface = true;
                        break;
                    }
                }

                for (Constructor constructor : clazz.getConstructors())
                {
                    if (constructor.getParameterTypes().length == 0)
                    {
                        hasDefaultConstructor = true;
                        break;
                    }
                }


                if (hasAnimalInterface && hasDefaultConstructor)
                {
                    Animal animal = (Animal) clazz.newInstance();
                    set.add(animal);
                }
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }

        return set;
    }
}
