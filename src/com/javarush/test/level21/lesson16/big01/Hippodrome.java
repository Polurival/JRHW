package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by jpolshchikov on 20.08.2015.
 */

public class Hippodrome
{

    private static ArrayList<Horse> horses = new ArrayList<Horse>();

    public static Hippodrome game;

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
            //System.out.println();
            //System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = null;
        for (int i = 1; i < horses.size(); i++) {
            if (horses.get(i).getDistance() > horses.get(i - 1).getDistance()) {
                winner = horses.get(i);
            } else {
                winner = horses.get(i - 1);
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args)
    {
        game = new Hippodrome();

        Horse veterHorse = new Horse("Ветер", 3, 0);
        Horse azartHorse = new Horse("Азарт", 3, 0);
        Horse iskraHorse = new Horse("Искра", 3, 0);

        horses.add(veterHorse);
        horses.add(azartHorse);
        horses.add(iskraHorse);

        game.run();
        game.printWinner();
    }
}
