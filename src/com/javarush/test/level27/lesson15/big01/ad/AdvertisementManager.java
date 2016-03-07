package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Collections;
import java.util.Comparator;

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
        Collections.sort(storage.list(), new Comparator<Advertisement>()
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

        int timeLeft = timeSeconds;
        for (Advertisement a : storage.list())
        {
            if (timeLeft < a.getDuration())
            {
                continue;
            }

            ConsoleHelper.writeMessage(a.getName() + " is displaying... "
                    + a.getAmountPerOneDisplaying() + ", "
                    + a.getAmountPerOneDisplaying() * 1000 / a.getDuration());

            timeLeft -= a.getDuration();
            a.revalidate();
        }

        if (timeLeft == timeSeconds)
        {
            throw new NoVideoAvailableException();
        }
    }
}
