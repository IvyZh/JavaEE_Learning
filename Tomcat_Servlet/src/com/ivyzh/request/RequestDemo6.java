package com.ivyzh.request;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求转发:一种在服务器内部的资源跳转方式
 *
 *	1. 步骤：
 * 		1. 通过request对象获取请求转发器对象：
 * RequestDispatcher getRequestDispatcher(String path)
 * 		2. 使用RequestDispatcher对象来进行转发：
 * forward(ServletRequest request, ServletResponse response)
 * 	2. 特点：
 * 		1. 浏览器地址栏路径不发生变化
 * 		2. 只能转发到当前服务器内部资源中。
 * 		3. 转发是一次请求
 *
 *
 */
@WebServlet("/request6")
public class RequestDemo6 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("request6被调用了");
        String name = (String) req.getAttribute("name");
        String age = (String) req.getAttribute("age");
        System.out.println(name+","+age);

        ServletContext servletContext = req.getServletContext();
        System.out.println("servletContext:"+servletContext);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
