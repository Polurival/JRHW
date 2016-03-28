package com.javarush.test.level31.lesson15.big01;

import com.javarush.test.level31.lesson15.big01.exception.WrongZipFileException;

import java.io.IOException;

/**
 * Created by
 * Polurival on 27.03.2016.
 */
public class Archiver
{
    public static void main(String[] args)
    {
        Operation operation = null;
        while (true) {
            try
            {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
            if (Operation.EXIT.equals(operation)) {
                break;
            }
        }
    }

    public static Operation askOperation() throws IOException
    {
        ConsoleHelper.writeMessage("Выберите операцию:\n " +
                Operation.CREATE.ordinal() + " - упаковать файлы в архив\n " +
                Operation.ADD.ordinal() + " - добавить файл в архив\n " +
                Operation.REMOVE.ordinal() + " - удалить файл из архива\n " +
                Operation.EXTRACT.ordinal() + " - распаковать архив\n " +
                Operation.CONTENT.ordinal() + " - просмотреть содержимое архива\n " +
                Operation.EXIT.ordinal() + " – выход");
        int operationOrdinal = ConsoleHelper.readInt();
        return Operation.values()[operationOrdinal];
    }
}
