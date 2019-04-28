package com.ivyzh.annotation.caculator;

/**
 * 计算器类
 */
public class Calculator {
    @Check(a=5,b=3)
    public int add(int a,int b){
        return a+b;
    }
    @Check
    public int sub(int a,int b){
        return a+b;
    }
    @Check
    public int mul(int a,int b){
        return a*b;
    }
    @Check(a=10,b=0)
    public int div(int a,int b){
        return a/b;
    }

    public void show(){
        System.out.println("show...");
    }
}
