package com.ivyzh.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * jsoup 是一款Java 的HTML解析器，可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，可通过DOM，CSS以及类似于jQuery的操作方法来取出和操作数据。
 */
public class JsoupDemo2 {

    public static void main(String[] args) throws IOException {

        String path = JsoupDemo2.class.getClassLoader().getResource("student.xml").getPath();
        Document document = Jsoup.parse(new File(path), "utf-8");
        Elements students = document.getElementsByTag("student");
        System.out.println(students.size());
        // 1. 获取student的name、age、sex
        for (Element student : students) {
            Elements name = student.getElementsByTag("name");
            Elements age = student.getElementsByTag("age");
            Elements sex = student.getElementsByTag("sex");
            String number = student.attr("number");
            System.out.println(name.text()+"---"+number+"---"+age.text()+"--"+sex.text());
        }



    }
}
