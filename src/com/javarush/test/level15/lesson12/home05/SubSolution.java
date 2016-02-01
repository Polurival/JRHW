package com.javarush.test.level15.lesson12.home05;

public class SubSolution extends Solution
{

    private SubSolution(Boolean B){}
    private SubSolution(boolean b){}
    private SubSolution(String s){}

    public SubSolution() {}
    public SubSolution(short s) { super(s); }
    public SubSolution(int i) { super(i); }

    protected SubSolution(long l) { super(l); }
    protected SubSolution(float f) { super(f); }
    protected SubSolution(double d) { super(d); }

    SubSolution(Short S) { super(S); }
    SubSolution(Integer I) { super(I); }
    SubSolution(Long L) { super(L); }
}
