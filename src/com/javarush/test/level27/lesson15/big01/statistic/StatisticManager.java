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

        public StatisticStorage() {
            map.put(EventType.COOKED_ORDER, new ArrayList<EventDataRow>());
            map.put(EventType.SELECTED_VIDEOS, new ArrayList<EventDataRow>());
            map.put(EventType.NO_AVAILABLE_VIDEO, new ArrayList<EventDataRow>());
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
        //TODO
    }

}
