package com.javarush.test.level18.lesson08.task04;

import java.io.*;
import java.nio.channels.FileChannel;

/* UnsupportedFileName
Измените класс TxtInputStream так, чтобы он работал только с txt-файлами (*.txt)
Например, first.txt или name.1.part3.txt
Если передан не txt-файл, например, file.txt.exe, то конструктор должен выбрасывать исключение UnsupportedFileNameException
*/

public class TxtInputStream extends FileInputStream {
    public TxtInputStream(String fileName) throws FileNotFoundException, UnsupportedFileNameException
    {
        super(fileName);
        if (!fileName.endsWith(".txt")) throw new UnsupportedFileNameException();
    }

    public TxtInputStream(File file) throws FileNotFoundException
    {
        super(file);
    }

    public TxtInputStream(FileDescriptor fdObj)
    {
        super(fdObj);
    }

    @Override
    public int read() throws IOException
    {
        return super.read();
    }

    @Override
    public int read(byte[] b) throws IOException
    {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException
    {
        return super.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException
    {
        return super.skip(n);
    }

    @Override
    public int available() throws IOException
    {
        return super.available();
    }

    @Override
    public void close() throws IOException
    {
        super.close();
    }

    @Override
    public FileChannel getChannel()
    {
        return super.getChannel();
    }

    @Override
    protected void finalize() throws IOException
    {
        super.finalize();
    }
}

