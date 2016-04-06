package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by
 * Polurival on 06.04.2016.
 */
public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try
        {
            this.path = Files.createTempFile(null,null);
            File file = path.toFile();
            file.deleteOnExit();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public long getFileSize()
    {
        try
        {
            return Files.size(path);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public void putEntry(Entry entry)
    {
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(path.toFile())))
        {
            ObjectOutput entryOutputStream = new ObjectOutputStream(os);
            for (Entry e = entry; e != null; e = e.next)
            {
                entryOutputStream.writeObject(e);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Entry getEntry()
    {
        Entry entry = null;
        try (InputStream is = new BufferedInputStream(new FileInputStream(path.toFile())))
        {
            if (Files.size(path) == 0)
            {
                return null;
            }
            ObjectInput entryInputStream = new ObjectInputStream(is);
            entry = (Entry) entryInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return entry;
    }

    public void remove()
    {
        try
        {
            Files.delete(path);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
