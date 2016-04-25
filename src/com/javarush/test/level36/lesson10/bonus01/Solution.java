package com.javarush.test.level36.lesson10.bonus01;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution
{
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName)
    {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException
    {
        Solution solution = new Solution("C:\\JavaRushHomeWork\\src\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException
    {
        ClassLoader classLoader = new ClassLoader()
        {
            @Override
            protected Class<?> findClass(String className) throws ClassNotFoundException
            {
                byte b[];
                try
                {
                    b = fetchClassFromFC(packageName + className);
                    return defineClass(null, b, 0, b.length);
                }
                catch (IOException e)
                {
                    return super.findClass(className);
                }
            }

            private byte[] fetchClassFromFC(String path) throws IOException
            {
                long length = new File(path).length();
                byte[] bytes = new byte[(int) length];

                try (InputStream is = new FileInputStream(path))
                {
                    int offset = 0;
                    int numRead;
                    while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
                    {
                        offset += numRead;
                    }
                }

                return bytes;
            }
        };

        if (!packageName.endsWith("\\"))
        {
            packageName += "\\";
        }
        String[] files = new File(packageName).list();

        for (String file : files)
        {
            Class clazz = classLoader.loadClass(file);
            hiddenClasses.add(clazz);
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key)
    {
        for (Class clazz : hiddenClasses)
        {
            if (clazz.getSimpleName().toLowerCase().startsWith(key))
            {
                try
                {
                    Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[0]);
                    constructor.setAccessible(true);
                    HiddenClass hiddenClass = (HiddenClass) constructor.newInstance();
                    return hiddenClass;
                }
                catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
