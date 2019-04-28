package com.ivyzh.reflect;

import com.ivyzh.reflect.domain.Person;
import com.ivyzh.reflect.domain.Person2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射的功能使用：
 *	1. 获取成员变量们
 * 		* Field[] getFields() ：获取所有public修饰的成员变量
 * 		* Field getField(String name)   获取指定名称的 public修饰的成员变量
 *
 * 		* Field[] getDeclaredFields()  获取所有的成员变量，不考虑修饰符
 * 		* Field getDeclaredField(String name)
 * 	2. 获取构造方法们
 * 		* Constructor<?>[] getConstructors()
 * 		* Constructor<T> getConstructor(类<?>... parameterTypes)
 *
 * 		* Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
 * 		* Constructor<?>[] getDeclaredConstructors()
 * 	3. 获取成员方法们：
 * 		* Method[] getMethods()
 * 		* Method getMethod(String name, 类<?>... parameterTypes)
 *
 * 		* Method[] getDeclaredMethods()
 * 		* Method getDeclaredMethod(String name, 类<?>... parameterTypes)
 *
 * 	4. 获取全类名
 * 		* String getName()
 */
public class ReflectDemo2 {
    public static void main(String[] args) throws Exception {
        // 获取Class对象的3种方式
        //1.Class.forName("");
        Class clzPerson = Class.forName("com.ivyzh.reflect.domain.Person2");
        System.out.println(clzPerson);

        // 1. 获取成员变量们
        Field[] fields = clzPerson.getFields();//获取所有public修饰的成员变量
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            System.out.println("field-->"+field);
        }
        Field fielda1 = clzPerson.getField("a1");//获取指定名称的 public修饰的成员变量
        System.out.println("fielda1-->"+fielda1);
        System.out.println("---------------------------");
        Field[] declaredFields = clzPerson.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            System.out.println("declaredField-->"+declaredField);
        }
        Field declaredFieldd = clzPerson.getDeclaredField("d");
        System.out.println("declaredFieldd-->"+declaredFieldd);
        System.out.println("---------------------------");
        // 1.1 设置成员变量们
        Person2 person = new Person2();
        Field fieldaa = clzPerson.getField("a");
        fieldaa.set(person,10);
        Field fieldName = clzPerson.getDeclaredField("name");
        fieldName.setAccessible(true);//暴力反射,忽略访问权限修饰符的安全检查
        fieldName.set(person,"zhangsan");
        System.out.println("person:"+person);
        // 1.2 获取成员们的值
        Object nameValue = fieldName.get(person);
        System.out.println("nameValue:"+nameValue);
        System.out.println("---------------------------");

        // 2. 获取Constructor
        Constructor[] constructors = clzPerson.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            Constructor constructor = constructors[i];
            System.out.println("constructor-->"+constructor);
        }
        Constructor[] declaredConstructors = clzPerson.getDeclaredConstructors();
        for (int i = 0; i < declaredConstructors.length; i++) {
            Constructor declaredConstructor = declaredConstructors[i];
            System.out.println("declaredConstructor-->"+declaredConstructor);
        }
        System.out.println("---------------------------");
        // 使用构造器创建对象
        Constructor constructor = clzPerson.getConstructor();//无参数构造器
        Object p1 = constructor.newInstance();
        System.out.println("p1:"+p1);
        //Constructor declaredConstructor = clzPerson.getDeclaredConstructor(String.class,int.class);
        Constructor declaredConstructor = clzPerson.getDeclaredConstructor( int.class);
        declaredConstructor.setAccessible(true);
        //Object p2 = declaredConstructor.newInstance("lisi",20);
        Object p2 = declaredConstructor.newInstance( 20);
        System.out.println("p2:"+p2);
        System.out.println("---------------------------");

        // 3. 获取Method

        Method[] methods = clzPerson.getMethods();

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            System.out.println("method-->"+method);//父类的方法也会被获取
        }
        Method[] declaredMethods = clzPerson.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            Method declaredMethod = declaredMethods[i];
            System.out.println("declaredMethod-->"+declaredMethod);//父类的方法不会被获取
        }

        Method setName = clzPerson.getDeclaredMethod("setName", String.class);
        Person2 person2 = new Person2();
        setName.setAccessible(true);
        setName.invoke(person2,"wangwu");
        System.out.println("person2:"+person2);
    }
}
