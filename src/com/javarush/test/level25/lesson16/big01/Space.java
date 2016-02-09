package com.javarush.test.level25.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by Polurival
 * on 07.02.2016.
 */
public class Space
{
    private int height;
    private int width;

    private SpaceShip ship;

    private ArrayList<Ufo> ufos = new ArrayList<Ufo>();
    private ArrayList<Rocket> rockets = new ArrayList<Rocket>();
    private ArrayList<Bomb> bombs = new ArrayList<Bomb>();

    public static Space game;


    public Space(int height, int width) {
        this.height = height;
        this.width = width;
    }


    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public SpaceShip getShip()
    {
        return ship;
    }

    public ArrayList<Ufo> getUfos()
    {
        return ufos;
    }

    public ArrayList<Rocket> getRockets()
    {
        return rockets;
    }

    public ArrayList<Bomb> getBombs()
    {
        return bombs;
    }


    public void setShip(SpaceShip ship)
    {
        this.ship = ship;
    }


    public void sleep(int ms) {

    }

    public void draw() {

    }

    public void run() {

    }


    public static void main(String[] args)
    {

    }
}
