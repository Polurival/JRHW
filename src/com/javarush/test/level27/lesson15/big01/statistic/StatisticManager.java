package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by
 * Polurival on 08.03.2016.
 */
public class StatisticManager
{
    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> map = new HashMap<>();

        private StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                map.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            map.get(data.getType()).add(data);
        }
    }

    private StatisticStorage statisticStorage = new StatisticStorage();

    private static StatisticManager instance = new StatisticManager();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

}
