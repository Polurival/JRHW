package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
        Document document = null;
        try
        {
            document = getDocument();
            Element template = document.getElementsByClass("template").first().clone();
            template.removeAttr("style");
            template.removeClass("template");

            for (Element tr : document.select(".vacancy"))
            {
                if (tr.hasClass("template"))
                {
                    continue;
                }
                tr.remove();
            }

            for (Vacancy vacancy : vacancies)
            {
                Element currentVacancyElement = template.clone();
                currentVacancyElement.getElementsByClass("city").first().text(vacancy.getCity());
                currentVacancyElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                currentVacancyElement.getElementsByClass("salary").first().text(vacancy.getSalary());
                currentVacancyElement.getElementsByTag("a").first().text(vacancy.getTitle());
                currentVacancyElement.getElementsByTag("a").first().attr("href", vacancy.getUrl());
                document.select(".vacancy.template").first().before(currentVacancyElement.outerHtml());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }
        return document != null ? document.html() : null;
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

    protected Document getDocument() throws IOException
    {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

}
