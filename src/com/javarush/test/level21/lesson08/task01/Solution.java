package com.javarush.test.level21.lesson08.task01;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* Глубокое клонирование карты
Клонируйтие объект класса Solution используя глубокое клонирование.
Данные в карте users также должны клонироваться.
*/
public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    protected Solution clone() throws CloneNotSupportedException
    {
        Solution cloneSol = new Solution();
        Map<String, User> cloneUsers = new LinkedHashMap<>();
        Map.Entry<String, User> pair;
        User cloneUser = new User(0, "");
        String cloneStr;

        Iterator<Map.Entry<String, User>> iterator = this.users.entrySet().iterator();

        while (iterator.hasNext()) {
            pair = iterator.next();
            cloneStr = pair.getKey();
            cloneUser.age = pair.getValue().age;
            cloneUser.name = pair.getValue().name;
            cloneUsers.put(cloneStr, cloneUser);

        }

        cloneSol.users = cloneUsers;



        return cloneSol;
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}
