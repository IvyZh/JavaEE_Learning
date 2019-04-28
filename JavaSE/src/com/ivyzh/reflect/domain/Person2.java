package com.ivyzh.reflect.domain;

public class Person2 {
    private String name;
    private int age;

    // 成员变量
    public int a;
    public int a1;
    protected int b;
    int c;
    private int d;


    // 构造函数
     Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }
    protected Person2(String name ) {
        this.name = name;

    }
    public  Person2() {
    }
    private Person2(int age ) {
        this.age = age;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("setName is call...");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 成员方法
    public void m1() {

    }
    protected void m2() {

    }
      void m3() {

    }
    private void m4() {

    }

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", a=" + a +
                ", a1=" + a1 +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}
