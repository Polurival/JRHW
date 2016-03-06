package com.javarush.test.level27.lesson15.big01.ad;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        if (storage.list().isEmpty())
        {
            throw new NoVideoAvailableException();
        } else
        {
            List<Object> videos = storage.list();
            Collections.sort(videos, new Comparator<Object>()
            {
                @Override
                public int compare(Object o1, Object o2)
                {
                    Advertisement a1 = (Advertisement) o1;
                    Advertisement a2 = (Advertisement) o2;
                    if ((a2.getAmountPerOneDisplaying() - a1.getAmountPerOneDisplaying()) > 0) {
                        return 1;
                    }
                    else if ((a2.getAmountPerOneDisplaying() - a1.getAmountPerOneDisplaying()) < 0) {
                        return -1;
                    } else {
                        double a1AmountPerOneSecond = (double) a1.getAmountPerOneDisplaying() / a1.getDuration();
                        double a2AmountPerOneSecond = (double) a2.getAmountPerOneDisplaying() / a2.getDuration();
                        if (a1AmountPerOneSecond > a2AmountPerOneSecond)
                        {
                            return 1;
                        }
                        else if (a1AmountPerOneSecond < a2AmountPerOneSecond)
                        {
                            return -1;
                        }
                        else {
                            return 0;
                        }
                    }
                }
            });

            List<Object> compatibleAd = new ArrayList<>();
            int leftedTime = timeSeconds;
            for (Object video : videos) {
                Advertisement a = (Advertisement) video;
                int aTime = a.getDuration();
                if (leftedTime >= (aTime / 60))
                {
                    leftedTime -= (aTime / 60);
                    if (leftedTime >= 0) {
                        compatibleAd.add(a);
                    }
                }

            }

            for (Object video : compatibleAd) {
                Advertisement a = (Advertisement) video;
                String name = a.getName();
                long amountPerOneDisplaying = a.getAmountPerOneDisplaying();
                double temp = (double) amountPerOneDisplaying / a.getDuration();
                double temp2 = new BigDecimal(temp).setScale(3, RoundingMode.DOWN).doubleValue();
                int amountPerOneSecond = (int) (temp2 * 1000);
                System.out.println(name + " is displaying... " + amountPerOneDisplaying + ", " + amountPerOneSecond);
                a.revalidate();
            }
        }
    }
}
