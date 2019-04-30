package com.ivyzh.jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 快捷查询方式：
 * 	1. selector:选择器
 * 		* 使用的方法：Elements	select​(String cssQuery)
 * 			* 语法：参考Selector类中定义的语法
 * 	2. XPath：XPath即为XML路径语言，它是一种用来确定XML（标准通用标记语言的子集）文档中某部分位置的语言
 * 		* 使用Jsoup的Xpath需要额外导入jar包。
 * 		* 查询w3cshool参考手册，使用xpath的语法完成查询
 */
public class JsoupDemo4 {

    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {

        String path = JsoupDemo4.class.getClassLoader().getResource("student.xml").getPath();
        Document document = Jsoup.parse(new File(path), "utf-8");
        JXDocument jxDocument = new JXDocument(document);


        //4.结合xpath语法查询
        //4.1查询所有student标签
        System.out.println("---------查询所有student标签-----------");
        List<JXNode> jxNodes = jxDocument.selN("//student");
        printJxNodes(jxNodes);


        //4.2查询所有student标签下的name标签
        System.out.println("---------查询所有student标签下的name标签-----------");
        List<JXNode> jxNodes1 = jxDocument.selN("//student/name");
        printJxNodes(jxNodes1);

        //4.3查询student标签下带有id属性的name标签
        System.out.println("----------查询student标签下带有id属性的name标签----------");
        List<JXNode> jxNodes2 = jxDocument.selN("//student/name[@id]");
        printJxNodes(jxNodes2);


        //4.4查询student标签下带有id属性的name标签 并且id属性值为itcast
        System.out.println("-----------查询student标签下带有id属性的name标签 并且id属性值为j_id---------");
        List<JXNode> jxNodes3 = jxDocument.selN("//student/name[@id='j_id']");
        printJxNodes(jxNodes3);

    }

    private static void printJxNodes(List<JXNode> jxNodes) {
        for (JXNode jxNode : jxNodes) {
            System.out.println(jxNode);
        }
    }
}
