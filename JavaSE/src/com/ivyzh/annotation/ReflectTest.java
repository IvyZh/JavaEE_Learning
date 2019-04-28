package com.ivyzh.annotation;

import java.lang.reflect.Method;

/**
 * 获取指定的注解
 * 		* getAnnotation(Class)
 */
@Pro(className = "com.ivyzh.reflect.domain.BMWCar",methodName = "run")
public class ReflectTest {
    public static void main(String[] args) throws  Exception {

        Pro annotation = ReflectTest.class.getAnnotation(Pro.class);
        String className = annotation.className();
        String methodName = annotation.methodName();
        System.out.println(className);
        System.out.println(methodName);

        Class<?> clz = Class.forName(className);
        Object car = clz.newInstance();
        Method declaredMethod = clz.getDeclaredMethod(methodName);
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(car);
    }
}
