package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes type)
    {
        if (ImageTypes.BMP == type)
            return new BmpReader();
        else if (ImageTypes.JPG == type)
            return new JpgReader();
        else if (ImageTypes.PNG == type)
            return new PngReader();
        else throw new IllegalArgumentException();

    }
}
