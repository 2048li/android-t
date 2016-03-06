package com.example.shentanli.reflectiontest;

/**
 * Created by shentanli on 3/5/16.
 */
public class Worker {
    private int age;
    private String name;
    private Student mStudent;
    public Worker(){
        super();
        mStudent = new Student("I am a student", 999 );
        System.out.println("----public worker()");

    }
    public Worker(int age, String name)
    {
        super();
        this.age = age;
        this.name = name;
        mStudent = new Student("I am a student", 999);
        System.out.println("----public workder(int age, String name){}");
    }

    public int getAge()
    {
        return age;

    }

    public void setAge(int age)
    {
        this.age = age;

    }

    public String getName()
    {
        return  name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String toString()
    {
        return "worker [age = " + age + ", name =" + name + "]";

    }

    public void printMessage(String name, int age, int salary)
    {
        System.out.println("name"+name+",age="+age+",salary="+salary);
    }




}
