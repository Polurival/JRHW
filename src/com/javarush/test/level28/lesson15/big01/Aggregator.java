package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;

import java.io.IOException;

/**
 * Created by
 * Polurival on 13.03.2016.
 */
public class Aggregator
{
    public static void main(String[] args) throws IOException
    {
        HtmlView view = new HtmlView();
        Provider hhProvider = new Provider(new HHStrategy());
        Model model = new Model(view, hhProvider);
        Controller controller = new Controller(model);
        view.setController(controller);

        view.userCitySelectEmulationMethod();
    }
}
