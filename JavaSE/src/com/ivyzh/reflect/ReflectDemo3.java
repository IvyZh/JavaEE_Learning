package com.ivyzh.reflect;

import com.ivyzh.reflect.domain.Person2;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 反射案例：
 * 需求：写一个"框架"，不能改变该类的任何代码的前提下，可以帮我们创建任意类的对象，并且执行其中任意方法
 * 		* 实现：
 * 			1. 配置文件
 * 			2. 反射
 * 		* 步骤：
 * 			1. 将需要创建的对象的全类名和需要执行的方法定义在配置文件中
 * 			2. 在程序中加载读取配置文件
 * 			3. 使用反射技术来加载类文件进内存
 * 			4. 创建对象
 * 			5. 执行方法
 */
public class ReflectDemo3 {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        ClassLoader classLoader = ReflectDemo3.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        properties.load(is);
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        Class<?> clz = Class.forName(className);
        Object instance = clz.newInstance();
        Method declaredMethod = clz.getDeclaredMethod(methodName);
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(instance);

    }
}
