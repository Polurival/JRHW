package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] passwordChar = new byte[1];

        boolean hasNumber = false;
        boolean hasUpperCaseChar = false;
        boolean hasLowerCaseChar = false;
        int charType;

        for (int i = 0; i < 8; i++) {
            if (hasNumber && hasUpperCaseChar && hasLowerCaseChar) {
                hasNumber = false;
                hasUpperCaseChar = false;
                hasLowerCaseChar = false;
            }

            charType = (int) (Math.random() * 3);

            switch (charType) {
                case 0:
                    if (hasNumber) {
                        i--;
                        continue;
                    }
                    passwordChar[0] = (byte) (Math.random() * 10 + 48);
                    hasNumber = true;
                    break;
                case 1:
                    if (hasUpperCaseChar) {
                        i--;
                        continue;
                    }
                    passwordChar[0] = (byte) (Math.random() * 26 + 65);
                    hasUpperCaseChar = true;
                    break;
                case 2:
                    passwordChar[0] = (byte) (Math.random() * 26 + 97);
                    if (hasLowerCaseChar) {
                        i--;
                        continue;
                    }
                    hasLowerCaseChar = true;
                    break;
            }

            try
            {
                baos.write(passwordChar);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return baos;
    }
}
