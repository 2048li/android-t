package com.example.shentanli.reflectiontest;

/**
 * Created by shentanli on 3/5/16.
 */
public class Student {
    private String name;
    private int age;
    public Student()
    {
        super();

    }
    public Student(String name, int age){
        super();
        this.name = name;
        this.age = age;

    }
    public String getName(){
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge()
    {
        this.age = age;
    }

    public String toString(){
        return String.format("Student [name=%s, age=%d]", name, age);
    }

    public void show(){
        System.out.println("--------call method show()");
    }



}
