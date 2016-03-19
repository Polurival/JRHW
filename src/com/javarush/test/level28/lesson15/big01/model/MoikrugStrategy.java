package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by
 * Polurival on 19.03.2016.
 */
public class MoikrugStrategy implements Strategy
{
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException
    {
        List<Vacancy> vacancies = new ArrayList<>();

        for (int i = 1; ; i++)
        {
            Document document = getDocument(String.format(URL_FORMAT, searchString, i), i);
            Elements plainVacancies = document.getElementsByClass("job");
            if (plainVacancies.size() == 0)
            {
                break;
            }
            Iterator iterator = plainVacancies.iterator();
            Element rawVacancy;
            while (iterator.hasNext())
            {
                Vacancy vacancy = new Vacancy();
                rawVacancy = (Element) iterator.next();

                vacancy.setTitle(rawVacancy.select(".title").first().text());

                Elements salaries = rawVacancy.select(".salary");
                if (salaries.size() > 0)
                {
                    vacancy.setSalary(salaries.first().text());
                } else
                {
                    vacancy.setSalary("");
                }

                Elements cities = rawVacancy.select(".location");
                if (cities.size() > 0) {
                    vacancy.setCity(cities.first().text());
                } else
                {
                    vacancy.setCity("");
                }

                vacancy.setCompanyName(rawVacancy.select(".company_name").first().getElementsByTag("a").first().text());
                vacancy.setSiteName("moikrug.ru");
                vacancy.setUrl("https://moikrug.ru"
                        + rawVacancy.select(".title").first().getElementsByTag("a").first().attr("href"));

                vacancies.add(vacancy);
            }
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36";
        String referrer = "google.ru";
        return Jsoup.connect(searchString).userAgent(userAgent).referrer(referrer).get();
    }
}
