package com.javarush.test.level20.lesson10.home07;

import java.io.*;

/* Переопределение сериализации в потоке
Сериализация/десериализация Solution не работает.
Исправьте ошибки не меняя сигнатуры методов и класса.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать экземпляр класса Solution
2) записать в него данные  - writeObject
3) сериализовать класс Solution  - writeObject(ObjectOutputStream out)
4) десериализовать, получаем новый объект
5) записать в новый объект данные - writeObject
6) проверить, что в файле есть данные из п.2 и п.5
*/
public class Solution implements Serializable, AutoCloseable
{
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException
    {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException
    {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.writeObject(fileName);
        //out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        this.fileName = (String) in.readObject();
        this.stream = new FileOutputStream(fileName, true);
        //in.close();
    }

    @Override
    public void close() throws Exception
    {
        System.out.println("Closing everything!");
        stream.close();
    }

    @Override
    public String toString()
    {
        return "Solution{" +
                "stream=" + stream +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception
    {
        Solution solution = new Solution("2.txt");
        solution.writeObject("old");
        System.out.println(solution);
        //solution.close();


        //SAVE
        FileOutputStream fileOutputStream = new FileOutputStream("1.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(solution);
        outputStream.flush();
        //outputStream.close();
        //LOAD
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        Solution newSolution;
        newSolution = (Solution) inputStream.readObject();
        System.out.println(newSolution);
        inputStream.close();
        newSolution.writeObject("new1");
        newSolution.writeObject("new2");

            /*try
            {
                Solution sol = new Solution("./1.txt");
                sol.writeObject("проверка");
                sol.writeObject("проверка1");
                sol.writeObject("проверка2");

                //ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./2.txt", true));
                oos.writeObject(sol);
                oos.close();

                //ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./2.txt"));
                Solution newSol = (Solution) ois.readObject();
                newSol.writeObject("----");
                newSol.writeObject("проверка3");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }*/
    }
}
