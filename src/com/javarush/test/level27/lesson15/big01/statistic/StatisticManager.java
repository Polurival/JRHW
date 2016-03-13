package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by
 * Polurival on 08.03.2016.
 */
public class StatisticManager
{
    private static final StatisticManager ourInstance = new StatisticManager();
    private final StatisticStorage storage = new StatisticStorage();
    private final Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    public void register(EventDataRow data) {
        storage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }


    public void watchAdvertisementStatistic() {
        double totalMoney = 0;
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, int[]> line : getAdvertisementProfit().entrySet()) {
            double profit = (double) line.getValue()[0] / 100;
            String prof = String.format(Locale.ENGLISH, "%.2f", profit);
            builder.append(line.getKey()).append(" - ").append(prof).append(System.lineSeparator());
            totalMoney += profit;
        }
        String tMoney = String.format(Locale.ENGLISH, "%.2f", totalMoney);
        builder.append("Total - ").append(tMoney);
        ConsoleHelper.writeMessage(builder.toString());
    }


    public void watchCookStatistic() {
        StringBuilder builder = new StringBuilder();
        Map<String, Map<String, int[]>> map = getCookTime();
        for (Map.Entry<String, Map<String, int[]>> entry : map.entrySet()) {
            builder.append(entry.getKey()).append(System.lineSeparator());
            for (Map.Entry<String, int[]> cook : entry.getValue().entrySet()) {
                builder.append(cook.getKey()).append(" - ").append(cook.getValue()[0]).append(" min")
                        .append(System.lineSeparator());
            }
        }
        ConsoleHelper.writeMessage(builder.toString());
    }

    private Map<String, int[]> getAdvertisementProfit() {
        List<EventDataRow> eventDataRows = storage.map.get(EventType.SELECTED_VIDEOS);
        DateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String, int[]> map = new TreeMap<>(Collections.reverseOrder());
        for (EventDataRow eventDataRow : eventDataRows) {
            VideoSelectedEventDataRow videoSelectedEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
            String date = format.format(videoSelectedEventDataRow.getDate());
            int money = (int) videoSelectedEventDataRow.getAmount();
            if (!map.containsKey(date))
                map.put(date, new int[]{money});
            else map.get(date)[0] += money;
        }
        return map;
    }

    private Map<String, Map<String, int[]>> getCookTime() {
        List<EventDataRow> eventDataRows = storage.map.get(EventType.COOKED_ORDER);
        DateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String, Map<String, int[]>> map = new TreeMap<>(Collections.reverseOrder());
        for (EventDataRow eventDataRow : eventDataRows) {
            CookedOrderEventDataRow cOEDRow = (CookedOrderEventDataRow) eventDataRow;
            String date = format.format(cOEDRow.getDate());
            String cookName = cOEDRow.getCookName();
            int duration = cOEDRow.getTime();
            int durationInMin = duration % 60 == 0 ? duration / 60 : duration / 60 + 1;

            if (!map.containsKey(date)) {
                Map<String, int[]> treeMap = new TreeMap<>();
                treeMap.put(cookName, new int[]{durationInMin});
                map.put(date, treeMap);
            } else {
                Map<String, int[]> treeMap = map.get(date);
                if (!treeMap.containsKey(cookName)) {
                    if (duration != 0)
                        treeMap.put(cookName, new int[]{durationInMin});
                } else {
                    treeMap.get(cookName)[0] += durationInMin;
                }
            }
        }
        return map;
    }

    private static class StatisticStorage {
        Map<EventType, List<EventDataRow>> map = new EnumMap<>(EventType.class);

        private StatisticStorage() {
            for (EventType type : EventType.values())
                map.put(type, new ArrayList<EventDataRow>());
        }

        private void put(EventDataRow data) {
            map.get(data.getType()).add(data);
        }
    }
}
