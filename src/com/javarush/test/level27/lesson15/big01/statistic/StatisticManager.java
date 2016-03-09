package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

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

        private Map<EventType, List<EventDataRow>> getData() {
            return map;
        }
    }

    private StatisticStorage statisticStorage = new StatisticStorage();

    private static StatisticManager instance = new StatisticManager();

    private Set<Cook> cooks = new HashSet<Cook>();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public Map<Date, Double> getStatisticForShownAdvertisement() {
        Map<EventType, List<EventDataRow>> storageMap = statisticStorage.getData();
        Map<Date, Double> resultMap = new TreeMap<>(new Comparator<Date>()
        {
            @Override
            public int compare(Date o1, Date o2)
            {
                return o2.compareTo(o1);
            }
        });
        if (storageMap != null)
        {
            List<EventDataRow> list = storageMap.get(EventType.SELECTED_VIDEOS);
            for (EventDataRow event : list) {
                VideoSelectedEventDataRow videoSelectedEvent = (VideoSelectedEventDataRow) event;
                Calendar cal = Calendar.getInstance();
                cal.setTime(videoSelectedEvent.getDate());
                GregorianCalendar g = new GregorianCalendar(cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                if (resultMap.containsKey(g.getTime()))
                {
                    double tmp = resultMap.get(g.getTime()) + (double) videoSelectedEvent.getAmount() / 100;
                    resultMap.remove(g.getTime());
                    resultMap.put(g.getTime(), tmp);
                }
                else
                {
                    resultMap.put(g.getTime(), (double) videoSelectedEvent.getAmount() / 100);
                }
            }
        }
        return resultMap;
    }

    public Map<Date, Map<String, Integer>> getStatisticForCooks() {
        Map<EventType, List<EventDataRow>> storageMap = statisticStorage.getData();
        Map<Date, Map<String, Integer>> resultMap = new TreeMap<>(new Comparator<Date>()
        {
            @Override
            public int compare(Date o1, Date o2)
            {
                return o2.compareTo(o1);
            }
        });
        if (storageMap != null) {
            List<EventDataRow> list = storageMap.get(EventType.COOKED_ORDER);
            for (EventDataRow event : list) {
                CookedOrderEventDataRow cookedOrderEvent = (CookedOrderEventDataRow) event;
                int cookingTimeMin = cookedOrderEvent.getTime();
                if (cookingTimeMin == 0) {
                    continue;
                }
                if (cookingTimeMin % 60 == 0) {
                    cookingTimeMin = cookingTimeMin / 60;
                } else {
                    cookingTimeMin = (cookingTimeMin / 60) + 1;
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(event.getDate());
                GregorianCalendar g = new GregorianCalendar(cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                if (resultMap.containsKey(g.getTime())) {
                    if (resultMap.get(g.getTime()).containsKey(cookedOrderEvent.getCookName())) {
                        int tmp = resultMap.get(g.getTime()).get(cookedOrderEvent.getCookName()) + cookingTimeMin;
                        resultMap.get(g.getTime()).remove(cookedOrderEvent.getCookName());
                        resultMap.get(g.getTime()).put(cookedOrderEvent.getCookName(), tmp);
                    } else {
                        resultMap.get(g.getTime()).put(cookedOrderEvent.getCookName(), cookingTimeMin);
                    }
                } else {
                    resultMap.put(g.getTime(), new TreeMap<String, Integer>(new Comparator<String>()
                    {
                        @Override
                        public int compare(String o1, String o2)
                        {
                            return o1.compareToIgnoreCase(o2);
                        }
                    }));
                    resultMap.get(g.getTime()).put(cookedOrderEvent.getCookName(), cookingTimeMin);
                }
            }
        }
        return resultMap;
    }
}
