package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.List;

public class University
{
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    public List<Student> getStudents()
    {
        return students;
    }

    public Student getStudentWithAverageGrade(double averageGrade)
    {
        for (Student student : students)
        {
            if (student.getAverageGrade() == averageGrade)
            {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade()
    {
        double max = Double.MIN_VALUE;
        Student studentWithMaxAverageGrade = null;
        for (Student student : students)
        {
            if (student.getAverageGrade() > max)
            {
                max = student.getAverageGrade();
                studentWithMaxAverageGrade = student;
            }
        }
        return studentWithMaxAverageGrade;
    }

    public Student getStudentWithMinAverageGrade()
    {
        double min = Double.MAX_VALUE;
        Student studentWithMinAverageGrade = null;
        for (Student student : students)
        {
            if (student.getAverageGrade() < min)
            {
                min = student.getAverageGrade();
                studentWithMinAverageGrade = student;
            }
        }
        return studentWithMinAverageGrade;
    }

    public void expel(Student student)
    {
        if (students.contains(student))
        {
            students.remove(student);
        }
    }
}
