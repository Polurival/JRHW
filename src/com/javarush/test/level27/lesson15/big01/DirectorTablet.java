package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by
 * Polurival on 08.03.2016.
        */
public class DirectorTablet
{
    public void printAdvertisementProfit() {
        Map<Date, Double> map = StatisticManager.getInstance().getStatisticForShownAdvertisement();
        double totalAmount = 0;

        for (Map.Entry<Date, Double> entry : map.entrySet()) {
            totalAmount += entry.getValue();
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f",
                    String.format(Locale.ENGLISH, "%1$td-%1$tb-%1$tY", entry.getKey()), entry.getValue()));
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", totalAmount));
    }

    public void printCookWorkloading() {
        Map<Date, Map<String, Integer>> map = StatisticManager.getInstance().getStatisticForCooks();

        for (Map.Entry<Date, Map<String, Integer>> entry1 : map.entrySet()) {
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%1$td-%1$tb-%1$tY", entry1.getKey()));
            for (Map.Entry<String, Integer> entry2 : entry1.getValue().entrySet()) {
                ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %d min",
                        entry2.getKey(), entry2.getValue()));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {

    }

    public void printArchivedVideoSet() {

    }
}
