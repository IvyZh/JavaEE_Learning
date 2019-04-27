package com.ivyzh.junit.test;

import com.ivyzh.junit.Calculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 计算器类
 *
 *  测试分类：
 * 	1. 黑盒测试：不需要写代码，给输入值，看程序是否能够输出期望的值。
 * 	2. 白盒测试：需要写代码的。关注程序具体的执行流程。更有技术含量
 *
 *  单元测试的使用步骤：
 *  * Junit使用：白盒测试
 * 	* 步骤：
 * 		1. 定义一个测试类(测试用例)
 * 			* 建议：
 * 				* 测试类名：被测试的类名Test		CalculatorTest
 * 				* 包名：xxx.xxx.xx.test		cn.itcast.test
 *
 * 		2. 定义测试方法：可以独立运行
 * 			* 建议：
 * 				* 方法名：test测试的方法名		testAdd()
 * 				* 返回值：void
 * 				* 参数列表：空参
 *
 * 		3. 给方法加@Test
 * 		4. 导入junit依赖环境
 *
 * 	* 判定结果：
 * 		* 红色：失败
 * 		* 绿色：成功
 * 		* 一般我们会使用断言操作来处理结果
 * 			* Assert.assertEquals(期望的结果,运算的结果);
 *
 * 	* 补充：（注意哪怕是程序出了错误，before和after都也会执行，而且执行顺序好像没有规律）
 * 		* @Before:
 * 			* 修饰的方法会在测试方法之前被自动执行
 * 		* @After:
 * 			* 修饰的方法会在测试方法执行之后自动被执行
 *
 */
public class CalculatorTest {

    @Before
    public void  init(){
        System.out.println("init....");
    }

    @After
    public void close0(){
        System.out.println("close0...");
    }
    @After
    public void close1(){
        System.out.println("close1...");
    }
    @After
    public void close2(){
        System.out.println("close2...");
    }

    @Test
    public void testAdd(){
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        System.out.println(result);
    }

    @Test
    public void testSub(){
        Calculator calculator = new Calculator();
        int result = calculator.sub(3, 1);
        //System.out.println(result);
        Assert.assertEquals(2,result);
    }

}
