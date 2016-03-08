package com.javarush.test.level27.lesson15.big01.statistic.event;

import java.util.Date;

/**
 * Created by
 * Polurival on 08.03.2016.
 */
public class NoAvailableVideoEventDataRow implements EventDataRow
{
    private int totalDuration;
    private Date currentDate;

    public NoAvailableVideoEventDataRow(int totalDuration)
    {
        this.totalDuration = totalDuration;
        currentDate = new Date();
    }
}
