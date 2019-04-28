package com.ivyzh.annotation.caculator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Method;

/**
 * 案例：简单的测试框架
 * 1.
 * 2.
 */

public class CaculatorTest {
    public static void main(String[] args) throws  Exception {

        Calculator calculator = new Calculator();
        Method[] declaredMethods = calculator.getClass().getDeclaredMethods();

        int bugNum = 0;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("bug.txt"));


        for (int i = 0; i < declaredMethods.length; i++) {
            Method declaredMethod = declaredMethods[i];
            if(declaredMethod.isAnnotationPresent(Check.class)){
                Check annotation = declaredMethod.getAnnotation(Check.class);
                int a = annotation.a();
                int b = annotation.b();
                try {
                    Object result =  declaredMethod.invoke(calculator,a,b);
                    System.out.println(a+" "+declaredMethod.getName()+" "+b+" result:"+result);
                }catch (Exception e){
                    e.printStackTrace();
                    bugNum++;
                    bufferedWriter.write(declaredMethod.getName()+" 出現異常了");
                    bufferedWriter.newLine();
                    bufferedWriter.write("異常名稱："+e.getCause().getClass().getSimpleName());
                    bufferedWriter.newLine();
                    bufferedWriter.write("異常原因："+e.getCause().getMessage());
                    bufferedWriter.newLine();
                    bufferedWriter.write("----------------------");
                    bufferedWriter.newLine();
                }

            }

        }

        bufferedWriter.write("本次運行一共出現"+bugNum+"次異常");
        bufferedWriter.flush();
        bufferedWriter.close();

    }
}
