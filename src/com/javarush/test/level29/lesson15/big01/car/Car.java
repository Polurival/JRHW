package com.javarush.test.level29.lesson15.big01.car;

import java.util.Date;

public abstract class Car
{
    public static final int TRUCK = 0;
    public static final int SEDAN = 1;
    public static final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers)
    {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public static Car create(int type, int numberOfPassengers)
    {
        switch (type)
        {
            case TRUCK:
                return new Truck(numberOfPassengers);
            case SEDAN:
                return new Sedan(numberOfPassengers);
            case CABRIOLET:
                return new Cabriolet(numberOfPassengers);
            default:
                return null;
        }
    }

    public void fill(double numberOfLiters) throws Exception
    {
        if (numberOfLiters < 0)
        {
            throw new Exception();
        }
        fuel += numberOfLiters;
    }

    public double getTripConsumption(Date date, int length, Date summerStart, Date summerEnd)
    {
        if (isSummer(date, summerStart, summerEnd))
        {
            return getSummerConsumption(length);
        } else
        {
            return getWinterConsumption(length);
        }
    }

    public int getNumberOfPassengersCanBeTransferred()
    {
        if (canPassengersBeTransferred())
        {
            return numberOfPassengers;
        }
        return 0;
    }

    public boolean isDriverAvailable()
    {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable)
    {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving()
    {
        if (numberOfPassengers > 0)
        {
            fastenPassengersBelts();
        }
        fastenDriverBelt();
    }

    public void fastenPassengersBelts()
    {
    }

    public void fastenDriverBelt()
    {
    }

    public abstract int getMaxSpeed();

    public boolean isSummer(Date date, Date summerStart, Date summerEnd)
    {
        return date.after(summerStart) && date.before(summerEnd);
    }

    public double getWinterConsumption(int length)
    {
        return winterFuelConsumption * length + winterWarmingUp;
    }

    public double getSummerConsumption(int length)
    {
        return summerFuelConsumption * length;
    }

    private boolean canPassengersBeTransferred()
    {
        return isDriverAvailable() && fuel > 0;
    }
}
