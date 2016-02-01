package com.javarush.test.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;

public class FileConsoleWriter extends FileWriter
{
    public FileConsoleWriter(String fileName) throws IOException
    {
        super(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException
    {
        super(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException
    {
        super(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException
    {
        super(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd)
    {
        super(fd);
    }

    @Override
    public void write(int c) throws IOException
    {
        String num = "" + ((char) c);
        write(num, 0, num.length());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException
    {
        String temp = "";
        for (char c : cbuf)
            temp += c;
        write(temp, off, len);
    }

    @Override
    public void write(String str, int off, int len) throws IOException
    {
        System.out.print(str.substring(off, off + len));
        super.write(str, off, len);
    }

    @Override
    public void write(char[] cbuf) throws IOException
    {
        write(cbuf, 0, cbuf.length);
    }

    @Override
    public void write(String str) throws IOException
    {
        write(str, 0, str.length());
    }

    public static void main(String[] args) throws IOException
    {
        String s = "Hello world";
        //char[] chars = new char[]{'4', '6', 'a'};
        //int number = 78;
        //char[] chars2 = new char[]{'4', '6', 'a', 'y', 'w'};
        //String str1 = "My first String";

        File file = new File("./1.txt");
        FileConsoleWriter fcw = new FileConsoleWriter(file);

        fcw.write(s);
        //fcw.write(chars);
        //fcw.write(number);
        //fcw.write(chars2, 1, 3);
        //fcw.write(str1, 3, 5);

        fcw.close();
    }
}
