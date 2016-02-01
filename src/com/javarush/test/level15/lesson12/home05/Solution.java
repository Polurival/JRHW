package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {

    private Solution(Boolean B){}
    private Solution(boolean b){}
    private Solution(String s){}

    public Solution() {}
    public Solution(short s){}
    public Solution(int i){}

    protected Solution(long l){}
    protected Solution(float f){}
    protected Solution(double d){}

    Solution(Short S){}
    Solution(Integer I){}
    Solution(Long L){}

}

