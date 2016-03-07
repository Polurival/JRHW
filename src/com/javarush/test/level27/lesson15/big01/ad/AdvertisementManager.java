package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.*;

/**
 * Created by
 * Polurival on 05.03.2016.
 */
public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private int timeSeconds;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos()
    {
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }

        List<List<Advertisement>> allCombinations = powerList(storage.list());
        List<Advertisement> bestList = chooseBestList(allCombinations);

        Collections.sort(bestList, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement a1, Advertisement a2)
            {
                int result = Long.compare(a2.getAmountPerOneDisplaying(), a1.getAmountPerOneDisplaying());
                if (result != 0) return result;

                long oneSecondCost1 = a1.getAmountPerOneDisplaying() * 1000 / a1.getDuration();
                long oneSecondCost2 = a2.getAmountPerOneDisplaying() * 1000 / a2.getDuration();

                return Long.compare(oneSecondCost1, oneSecondCost2);
            }
        });

        for (Advertisement a : bestList)
        {
            ConsoleHelper.writeMessage(a.getName() + " is displaying... "
                    + a.getAmountPerOneDisplaying() + ", "
                    + a.getAmountPerOneDisplaying() * 1000 / a.getDuration());

            a.revalidate();
        }
    }

    private List<Advertisement> chooseBestList(List<List<Advertisement>> allCombinations)
    {
        Iterator iterator = allCombinations.iterator();
        while (iterator.hasNext()) {
            List<Advertisement> list = (List<Advertisement>) iterator.next();
            if (list.isEmpty()) {
                iterator.remove();
            }
            int totalDuration = 0;
            for (Advertisement ad : list) {
                totalDuration += ad.getDuration();
            }
            if (totalDuration > timeSeconds) {
                iterator.remove();
            }
        }

        Collections.sort(allCombinations, new Comparator<List<Advertisement>>()
        {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2)
            {
                long sumA1 = 0;
                long sumA2 = 0;
                int durA1 = 0;
                int durA2 = 0;
                for (Advertisement a1 : o1) {
                    sumA1 += a1.getAmountPerOneDisplaying();
                    durA1 += a1.getDuration();
                }
                for (Advertisement a2 : o2) {
                    sumA2 += a2.getAmountPerOneDisplaying();
                    durA2 += a2.getDuration();
                }
                long resultSum = sumA2 - sumA1;
                if (resultSum != 0) {
                    return (int) resultSum;
                }

                int resultDur = durA2 - durA1;
                if (resultDur != 0) {
                    return resultDur;
                }

                return o1.size() - o2.size();
            }
        });

        return allCombinations.get(0);
    }

    private <Advertisement> List<List<Advertisement>> powerList(List<Advertisement> originalList) {
        List<List<Advertisement>> lists = new ArrayList<List<Advertisement>>();
        if (originalList.isEmpty()) {
            lists.add(new ArrayList<Advertisement>());
            return lists;
        }
        List<Advertisement> list = new ArrayList<Advertisement>(originalList);
        Advertisement head = list.get(0);
        List<Advertisement> rest = new ArrayList<Advertisement>(list.subList(1, list.size()));
        for (List<Advertisement> l : powerList(rest)) {
            List<Advertisement> newList = new ArrayList<Advertisement>();
            newList.add(head);
            newList.addAll(l);
            lists.add(newList);
            lists.add(l);
        }
        return lists;
    }
}
