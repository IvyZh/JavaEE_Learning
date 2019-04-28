package com.ivyzh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 ** 元注解：用于描述注解的注解
 * 	* @Target：描述注解能够作用的位置
 * 		* ElementType取值：
 * 			* TYPE：可以作用于类上
 * 			* METHOD：可以作用于方法上
 * 			* FIELD：可以作用于成员变量上
 * 	* @Retention：描述注解被保留的阶段
 * 		* @Retention(RetentionPolicy.RUNTIME)：当前被描述的注解，会保留到class字节码文件中，并被JVM读取到
 *      * @Retention(RetentionPolicy.CLASS)：当前被描述的注解，会保留到class字节码文件中，但不会被JVM读取到
 *      * @Retention(RetentionPolicy.SOURCE)：当前被描述的注解，不会保留到class字节码文件中，但不会被JVM读取到
 * （三个阶段：source、class、runtime）
 *  * @Documented：描述注解是否被抽取到api文档中
 * 	* @Inherited：描述注解是否被子类继承

 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoDemo3 {
  int value();
}
