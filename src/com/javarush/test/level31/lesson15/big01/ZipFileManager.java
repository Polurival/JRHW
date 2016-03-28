package com.javarush.test.level31.lesson15.big01;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by
 * Polurival on 27.03.2016.
 */
public class ZipFileManager
{
    private Path zipFile;

    public ZipFileManager(Path zipFile)
    {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception
    {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile)))
        {
            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
            zipOutputStream.putNextEntry(zipEntry);

            int len;
            byte[] buffer = new byte[2048];
            try (InputStream inputStream = Files.newInputStream(source))
            {
                while (inputStream.available() > 0)
                {
                    len = inputStream.read(buffer);
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
        }
    }
}
