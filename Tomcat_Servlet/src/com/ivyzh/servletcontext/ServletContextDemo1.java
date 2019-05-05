package com.ivyzh.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 概念：代表整个web应用，可以和程序的容器(服务器)来通信
 * 2. 获取：
 * 	1. 通过request对象获取
 * 		request.getServletContext();
 * 	2. 通过HttpServlet获取
 * 		this.getServletContext();
 * 3. 功能：
 * 	1. 获取MIME类型：
 * 	2. 域对象：共享数据
 * 	3. 获取文件的真实(服务器)路径
 */
@WebServlet("/servletContext1")
public class ServletContextDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servletContext1");

        ServletContext servletContext1 = req.getServletContext();
        ServletContext servletContext2 = this.getServletContext();

        System.out.println(servletContext1);
        System.out.println(servletContext2);
        System.out.println(servletContext1==servletContext2);

        // 1. 获取MIME类型：
        String fileName1 = "a.jpg";
        String fileName2 = "a.avi";
        String fileName3 = "a.txt";
        String mimeType1 = servletContext1.getMimeType(fileName1);
        String mimeType2 = servletContext1.getMimeType(fileName2);
        String mimeType3 = servletContext1.getMimeType(fileName3);
        System.out.println(mimeType1+"      "+mimeType2+"      "+mimeType3);
        System.out.println("---------------");


//        2. 域对象：共享数据
//        1. setAttribute(String name,Object value)
//        2. getAttribute(String name)
//        3. removeAttribute(String name)
//                * ServletContext对象范围：所有用户所有请求的数据


        // 3. 获取文件的真实(服务器)路径
//        1. 方法：String getRealPath(String path)
//        String b = context.getRealPath("/b.txt");//web目录下资源访问
//        System.out.println(b);
//        String c = context.getRealPath("/WEB-INF/c.txt");//WEB-INF目录下的资源访问
//        System.out.println(c);
//        String a = context.getRealPath("/WEB-INF/classes/a.txt");//src目录下的资源访问
//        System.out.println(a);

        String realPath1 = servletContext1.getRealPath("/login.html");
        System.out.println(realPath1);

        String realPath2 = servletContext1.getRealPath("/WEB-INF/login.html");
        System.out.println(realPath2);
        String realPath3 = servletContext1.getRealPath("/WEB-INF/classes/druid.properties");
        System.out.println(realPath3);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
