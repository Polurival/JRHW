package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by
 * Polurival on 08.03.2016.
 */
public class StatisticEventManager
{
    private class StatisticStorage
    {
        private Map<EventType, List<EventDataRow>> map = new HashMap<>();

        private StatisticStorage()
        {
            for (EventType eventType : EventType.values())
            {
                map.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data)
        {
            map.get(data.getType()).add(data);
        }

        private Map<EventType, List<EventDataRow>> getData()
        {
            return map;
        }
    }

    private StatisticStorage statisticStorage = new StatisticStorage();

    private static StatisticEventManager instance = new StatisticEventManager();

    public static StatisticEventManager getInstance()
    {
        return instance;
    }

    private StatisticEventManager()
    {
    }

    public void register(EventDataRow data)
    {
        statisticStorage.put(data);
    }

    public Map<String, Double> getStatisticForShownAdvertisement()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<EventType, List<EventDataRow>> storageMap = statisticStorage.getData();
        List<EventDataRow> list = storageMap.get(EventType.SELECTED_VIDEOS);

        Map<String, Double> map = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow event : list)
        {
            VideoSelectedEventDataRow videoSelectedEvent = (VideoSelectedEventDataRow) event;
            String date = dateFormat.format(videoSelectedEvent.getDate());
            double amount = (double) videoSelectedEvent.getAmount() / 100;

            if (map.containsKey(date))
            {
                map.put(date, map.get(date) + amount);
            } else
            {
                map.put(date, amount);
            }
        }
        return map;
    }

    public Map<String, Map<String, Integer>> getStatisticForCooks()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<EventType, List<EventDataRow>> storageMap = statisticStorage.getData();
        List<EventDataRow> list = storageMap.get(EventType.COOKED_ORDER);

        Map<String, Map<String, Integer>> map = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow event : list)
        {
            CookedOrderEventDataRow cookedOrderEvent = (CookedOrderEventDataRow) event;
            String date = dateFormat.format(cookedOrderEvent.getDate());
            String cookName = cookedOrderEvent.getCookName();
            int cookingTime = cookedOrderEvent.getTime();
            int cookingTimeMin = (cookingTime % 60 == 0) ? (cookingTime / 60) : (cookingTime / 60 + 1);

            if (map.containsKey(date))
            {
                Map<String, Integer> temp = map.get(date);
                if (temp.containsKey(cookName))
                {
                    temp.put(cookName, temp.get(cookName) + cookingTimeMin);
                } else
                {
                    temp.put(cookName, cookingTimeMin);
                }
                map.put(date, temp);
            } else
            {
                Map<String, Integer> temp = new TreeMap<>();
                temp.put(cookName, cookingTimeMin);
                map.put(date, temp);
            }
        }
        return map;
    }
}
