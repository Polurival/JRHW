package com.javarush.test.level19.lesson03.task04;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1978

Подсказка: воспользуйтесь классом Calendar
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner {

        private Scanner scanner;

        public PersonScannerAdapter(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException
        {
            String[] s = scanner.nextLine().split(" ");
            String lastName = s[0];
            String firstName = s[1];
            String middleName = s[2];
            int day = Integer.parseInt(s[3]);
            int month = Integer.parseInt(s[4]) - 1;
            int year = Integer.parseInt(s[5]);

            Calendar calendar = new GregorianCalendar(year, month, day);
            Date birthDate = calendar.getTime();

            Person person = new Person(firstName, middleName, lastName, birthDate);
            return person;
        }

        @Override
        public void close() throws IOException
        {
            this.scanner.close();
        }

        public static void main(String[] args) throws IOException
        {
            File file = new File("C:\\tmp\\1.txt");
            FileInputStream fis = new FileInputStream(file);
            Scanner scan = new Scanner(fis);
            PersonScanner adapter = new PersonScannerAdapter(scan);
            System.out.println(adapter.read());
        }
    }
}
