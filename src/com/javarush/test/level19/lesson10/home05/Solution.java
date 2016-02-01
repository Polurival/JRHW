package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит слова, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));

        boolean first= true;
        while (br.ready()) {
            String[] s = br.readLine().split(" ");
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s[i].length(); j++) {
                    if (Character.isDigit(s[i].charAt(j)))
                        if (first) {
                            bw.write(s[i]);
                            first = false;
                            break;
                        } else {
                            bw.write(" " + s[i]);
                            break;
                        }
                }
            }
        }

        br.close();
        bw.close();

    }
}
