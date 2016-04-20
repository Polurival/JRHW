package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

/**
 * Created by Polurival
 * on 17.04.16.
 */
public class Model
{
    public static int FIELD_SELL_SIZE = 20;

    private EventListener eventListener;

    private GameObjects gameObjects;
    private int currentLevel = 1;

    private LevelLoader levelLoader = new LevelLoader(Paths.get("./src/" +
            getClass().getPackage().getName()
                    .replaceAll("\\.", "/")
                    .replace("model", "res/levels.txt")));

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void restartLevel(int level)
    {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart()
    {
        restartLevel(currentLevel);
    }

    public void startNextLevel()
    {
        currentLevel++;
        restart();
    }

    public void move(Direction direction)
    {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction))
        {
            return;
        }
        if (checkBoxCollision(direction))
        {
            return;
        }

        int newX = 0;
        int newY = 0;
        switch (direction)
        {
            case UP:
                newY -= FIELD_SELL_SIZE;
                break;
            case DOWN:
                newY += FIELD_SELL_SIZE;
                break;
            case LEFT:
                newX -= FIELD_SELL_SIZE;
                break;
            case RIGHT:
                newX += FIELD_SELL_SIZE;
                break;
        }

        player.move(newX, newY);

        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {
        for (Wall wall : gameObjects.getWalls())
        {
            if (gameObject.isCollision(wall, direction))
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction)
    {
        Player player = gameObjects.getPlayer();
        Set<Box> boxes = gameObjects.getBoxes();

        for (Box box : boxes)
        {
            if (player.isCollision(box, direction))
            {
                for (Box secondBox : boxes)
                {
                    if (!(box.getX() == secondBox.getX() && box.getY() == secondBox.getY()))
                    {
                        if (box.isCollision(secondBox, direction))
                        {
                            return true;
                        }
                    }
                }

                if (checkWallCollision(box, direction))
                {
                    return true;
                }

                int newX = 0;
                int newY = 0;
                switch (direction)
                {
                    case UP:
                        newY -= FIELD_SELL_SIZE;
                        break;
                    case DOWN:
                        newY += FIELD_SELL_SIZE;
                        break;
                    case LEFT:
                        newX -= FIELD_SELL_SIZE;
                        break;
                    case RIGHT:
                        newX += FIELD_SELL_SIZE;
                        break;
                }
                box.move(newX, newY);
            }
        }
        return false;
    }

    public void checkCompletion()
    {
        int count = 0;
        for (Home home : gameObjects.getHomes())
        {
            for (Box box : gameObjects.getBoxes())
            {
                if (home.getX() == box.getX() && home.getY() == box.getY())
                {
                    count++;
                    break;
                }
            }
        }
        if (count == gameObjects.getHomes().size())
        {
            eventListener.levelCompleted(currentLevel);
        }
    }
}
