package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.*;
import java.util.List;

/**
 * Created by
 * Polurival on 17.03.2016.
 */
public class HtmlView implements View
{
    private Controller controller;

    private final String filePath =
            "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies)
    {
        String fileContent = getUpdatedFileContent(vacancies);
        updateFile(fileContent);
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() throws IOException
    {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies)
    {
        return null;
    }

    private void updateFile(String fileContent)
    {
        try (FileWriter fileWriter = new FileWriter(filePath))
        {
            if (fileContent != null)
            {
                fileWriter.write(fileContent);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
