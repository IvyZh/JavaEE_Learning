package com.ivyzh.annotation;

/**
 * * 概念：说明程序的。给计算机看的
 * 			1. 如果定义属性时，使用default关键字给属性默认初始化值，则使用注解时，可以不进行属性的赋值。
 * 			2. 如果只有一个属性需要赋值，并且属性的名称是value，则value可以省略，直接定义值即可。
 * 			3. 数组赋值时，值使用{}包裹。如果数组中只有一个值，则{}可以省略

 */
public @interface AnnoDemo2 {
    int age() default 18;
  int value();
  String[] names();
}
