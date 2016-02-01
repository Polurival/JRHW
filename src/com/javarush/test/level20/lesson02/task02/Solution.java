package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            //File your_file_name = File.createTempFile("your_file_name", null);
            File your_file_name = new File("C:\\tmp\\1.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user1 = new User();
            user1.setFirstName("Ivan");
            user1.setLastName("Ivanov");
            user1.setBirthDate(new Date(2000 - 1900, 2, 18));
            user1.setMale(true);
            user1.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user1);
            User user2 = new User();
            user2.setFirstName("Petra");
            user2.setLastName("Petrova");
            user2.setBirthDate(new Date(2001 - 1900, 4, 7));
            user2.setMale(false);
            user2.setCountry(User.Country.OTHER);
            javaRush.users.add(user2);

            System.out.println("Сохранено:");
            System.out.println(javaRush.toString());

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println("Загружено:");
            System.out.println(loadedObject.toString());

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter writer = new PrintWriter(outputStream);
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

            for (User user : users) {
                writer.println(user.getFirstName());
                writer.println(user.getLastName());
                writer.println(format.format(user.getBirthDate()));
                writer.println(user.isMale());
                writer.println(user.getCountry().getDisplayedName());
            }

            writer.close();

        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

            while (reader.ready()) {
                User user = new User();
                user.setFirstName(reader.readLine());
                user.setLastName(reader.readLine());
                user.setBirthDate(format.parse(reader.readLine()));
                user.setMale(Boolean.parseBoolean(reader.readLine()));
                user.setCountry(User.Country.valueOf(reader.readLine().toUpperCase()));
                users.add(user);
            }

            reader.close();
        }

        @Override
        public String toString()
        {
            return "JavaRush{" +
                    "users=" + users +
                    '}';
        }
    }
}
