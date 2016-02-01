package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Не забудьте закрыть все потоки
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String file = reader.readLine();
            if ("exit".equals(file)) break;
            new ReadThread(file).start();
        }
        reader.close();
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        @Override
        public void run() {
            try
            {
                HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
                FileInputStream fis = new FileInputStream(fileName);
                while (fis.available() > 0) {
                    int b = fis.read();
                    if (!hm.containsKey(b)) {
                        hm.put(b, 1);
                    } else {
                        hm.put(b, hm.get(b) + 1);
                    }
                }

                int maxValue = Integer.MIN_VALUE;
                int maxKey = 0;
                for (Map.Entry<Integer, Integer> pair : hm.entrySet()) {
                    if (pair.getValue() > maxValue) {
                        maxValue = pair.getValue();
                        maxKey = pair.getKey();
                    }
                }
                resultMap.put(fileName, maxKey);

                fis.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }
}
