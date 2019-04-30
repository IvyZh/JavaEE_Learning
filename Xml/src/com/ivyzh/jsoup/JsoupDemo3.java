package com.ivyzh.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * 快捷查询方式：
 * 	1. selector:选择器
 * 		* 使用的方法：Elements	select​(String cssQuery)
 * 			* 语法：参考Selector类中定义的语法
 * 	2. XPath：XPath即为XML路径语言，它是一种用来确定XML（标准通用标记语言的子集）文档中某部分位置的语言
 * 		* 使用Jsoup的Xpath需要额外导入jar包。
 * 		* 查询w3cshool参考手册，使用xpath的语法完成查询
 */
public class JsoupDemo3 {

    public static void main(String[] args) throws IOException {

        String path = JsoupDemo3.class.getClassLoader().getResource("student.xml").getPath();
        Document document = Jsoup.parse(new File(path), "utf-8");

        Elements names = document.select("name");//元素名选择器
        for (Element name : names) {
            System.out.println(name.text());
        }
        System.out.println("------------");
        Elements names2 = document.select("#itcast");//id选择器
        for (Element name : names2) {
            System.out.println(name);
        }
        System.out.println("------------");
        // 获取student标签并且numbder属性为heima_0001的子标签
        Elements stu = document.select("student[number=\"heima_0001\"]");
        for (Element element : stu) {
            System.out.println(element);
        }
        System.out.println("------------");


        // 获取student标签并且numbder属性为heima_0001的子标签的age标签
        Elements stu1 = document.select("student[number=\"heima_0001\"] >age");
        for (Element element : stu1) {
            System.out.println(element);
        }
    }
}
