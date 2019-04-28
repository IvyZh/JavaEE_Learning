package com.ivyzh.reflect;

import com.ivyzh.reflect.domain.Person;

/**
 * 反射的基本使用：
 * 反射：将类的各个组成部分封装为其他对象，这就是反射机制
 * Java代码在计算机中经历的三个阶段
 * 1. Source源码阶段（.java-->.class字节码）
 * 2. Class类对象阶段（类加载器加载到内存）
 * 3. Runtime运行时阶段 （创建对象）
 *
 * 获取Class对象的3种方式
 * 	1. Class.forName("全类名")：将字节码文件加载进内存，返回Class对象
 * 		* 多用于配置文件，将类名定义在配置文件中。读取文件，加载类
 * 	2. 类名.class：通过类名的属性class获取
 * 		* 多用于参数的传递
 * 	3. 对象.getClass()：getClass()方法在Object类中定义着。
 * 		* 多用于对象的获取字节码的方式
 *
 * 	注意：同一个字节码文件(*.class)在一次程序运行过程中，只会被加载一次，不论通过哪一种方式获取的Class对象都是同一个。
 *
 */
public class ReflectDemo1 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 获取Class对象的3种方式
        //1.Class.forName("");
        Class clz1 = Class.forName("com.ivyzh.reflect.domain.Person");
        System.out.println(clz1);

        //2.类名.class
        Class clz2 = Person.class;
        System.out.println(clz2);

        //3. 对象.getClass()
        Person person = new Person();
        Class clz3 = person.getClass();
        System.out.println(clz3);

        System.out.println(clz1 ==clz2);
        System.out.println(clz1 ==clz3);

        /**
         * 控制台輸出結果：
             class com.ivyzh.reflect.domain.Person
             class com.ivyzh.reflect.domain.Person
             class com.ivyzh.reflect.domain.Person
             true
             true

         */
    }
}
