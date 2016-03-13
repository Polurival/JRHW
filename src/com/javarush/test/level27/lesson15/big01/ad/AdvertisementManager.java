package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

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

    public void processVideos() throws NoVideoAvailableException
    {
        List<Advertisement> list = getListForWatching();
        StatisticManager.getInstance().
                register(new VideoSelectedEventDataRow(list, getTotalMoney(list), getTotalDuration(list)));
        for (Advertisement adv : list)
        {
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
                    adv.getName(),
                    adv.getAmountPerOneDisplaying(),
                    adv.getAmountPerOneDisplaying() * 1000 / adv.getDuration()));
            adv.revalidate();
        }
    }

    private List<Advertisement> getListForWatching() throws NoVideoAvailableException
    {

        Comparator<Advertisement> advertisementComparator = new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                long sum1 = o1.getAmountPerOneDisplaying();
                long sum2 = o2.getAmountPerOneDisplaying();
                if (o1.getAmountPerOneDisplaying() != o2.getAmountPerOneDisplaying())
                    return Long.compare(sum2, sum1);
                else
                {
                    long sumSec1 = o1.getDuration() != 0 ? sum1 * 1000 / o1.getDuration() : 0L;
                    long sumSec2 = o2.getDuration() != 0 ? sum2 * 1000 / o2.getDuration() : 0L;
                    return Long.compare(sumSec1, sumSec2);
                }
            }
        };
        int count = 0;
        List<Advertisement> sourceList = storage.list();
        for (Advertisement advertisement : sourceList)
        {
            if (advertisement.getHits() <= 0)
                count++;
        }
        if (count == sourceList.size()) throw new NoVideoAvailableException();

        List<Advertisement> bestList = filterList(sourceList);
        Collections.sort(bestList, advertisementComparator);
        return bestList;
    }

    private List<Advertisement> filterList(List<Advertisement> list) throws NoVideoAvailableException
    {
        //filter the source list;
        List<Advertisement> correctList = new ArrayList<>(list.size());
        for (Advertisement adv : list)
        {
            if (adv.getHits() > 0 && adv.getDuration() <= timeSeconds)
                correctList.add(adv);
        }

        if (correctList.isEmpty()) throw new NoVideoAvailableException();
        //Create comparator ;
        Comparator<List<Advertisement>> bestListComparator = new Comparator<List<Advertisement>>()
        {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2)
            {
                if (getTotalMoney(o1) != getTotalMoney(o2))
                    return Long.compare(getTotalMoney(o2), getTotalMoney(o1));
                else if (getTotalDuration(o1) != getTotalDuration(o2))
                    return Integer.compare(getTotalDuration(o2), getTotalDuration(o1));
                else return Integer.compare(o1.size(), o2.size());
            }
        };

        int initialCapacity = (int) Math.pow(2, correctList.size() / 2);

        return getBestList(correctList, new BitSet(), 0,
                new PriorityQueue<>(initialCapacity, bestListComparator)).peek();
    }


    private PriorityQueue<List<Advertisement>> getBestList(final List<Advertisement> sourceList, final BitSet bitSet,
                                                           int j, final PriorityQueue<List<Advertisement>> queue)
    {
        //condition for end of recursion;
        if (j == sourceList.size())
            return queue;

        else
        {
            //Algorithm from Knuth for code gray generation;
            List<Advertisement> list = putToList(sourceList, bitSet);
            if (getTotalDuration(list) <= timeSeconds)
                queue.add(list);

            bitSet.flip(sourceList.size());

            if (bitSet.get(sourceList.size()))
                j = 0;
            else j = bitSet.nextSetBit(0) + 1;
            bitSet.flip(j);

            return getBestList(sourceList, bitSet, j, queue);
        }
    }


    private int getTotalDuration(List<Advertisement> o1)
    {
        int totalDuration = 0;
        for (Advertisement adv : o1) totalDuration += adv.getDuration();
        return totalDuration;
    }

    private long getTotalMoney(List<Advertisement> o1)
    {
        long sum = 0L;
        for (Advertisement adv : o1) sum += adv.getAmountPerOneDisplaying();
        return sum;
    }

    private List<Advertisement> putToList(final List<Advertisement> sourceList, final BitSet bitSet)
    {
        List<Advertisement> list = new ArrayList<>();
        for (int i = 0; i < sourceList.size(); ++i)
        {
            if (bitSet.get(i))
                list.add(sourceList.get(i));
        }
        return list;
    }
}
