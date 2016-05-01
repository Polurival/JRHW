package com.javarush.test.level40.lesson06.task02;

/* Принадлежность точки многоугольнику
Дан многоугольник, заданный координатами своих вершин.
Ребра многоугольника не пересекаются.
Необходимо реализовать метод isPointInPolygon(Point point, List<Point> polygon), который проверит,
принадлежит ли переданная точка многоугольнику.
*/

import java.util.ArrayList;
import java.util.List;

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        //напишите тут ваш код
        int mulCheck = 1;
        for (int i = 0; i < polygon.size(); i++)
        {
            Point a = polygon.get(i);
            Point b;
            if (i == polygon.size() - 1)
            {
                b = polygon.get(0);
            } else {
                b = polygon.get(i + 1);
            }
            mulCheck *= check(a, b, point);
        }
        return mulCheck != 1;
    }

    private static int check(Point a, Point b, Point point)
    {
        long ax = a.x - point.x;
        long ay = a.y - point.y;
        long bx = b.x - point.x;
        long by = b.y - point.y;
        int s = Long.signum(ax * by - ay * bx);
        if (s == 0 && (ay == 0 || by == 0) && ax * bx <= 0)
            return 0;
        if (ay < 0 ^ by < 0)
        {
            if (by < 0)
                return s;
            return -s;
        }
        return 1;
    }
}
