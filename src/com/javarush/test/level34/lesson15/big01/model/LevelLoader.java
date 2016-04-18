package com.javarush.test.level34.lesson15.big01.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Polurival
 * on 18.04.16.
 */
public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level)
    {
        Set<Wall> walls = new HashSet<>();
        walls.add(new Wall(30, 30));
        walls.add(new Wall(30, 50));
        walls.add(new Wall(30, 70));
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(90, 90));
        Set<Home> homes = new HashSet<>();
        homes.add(new Home(130, 170));
        Player player = new Player(150, 150);
        return new GameObjects(walls, boxes, homes, player);
    }
}
