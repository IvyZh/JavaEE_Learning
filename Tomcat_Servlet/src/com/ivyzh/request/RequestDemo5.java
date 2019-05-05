package com.ivyzh.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * 请求转发:一种在服务器内部的资源跳转方式
 *
 *	1. 步骤：
 * 		1. 通过request对象获取请求转发器对象：
 *          RequestDispatcher getRequestDispatcher(String path)
 * 		2. 使用RequestDispatcher对象来进行转发：
 *          forward(ServletRequest request, ServletResponse response)
 * 	2. 特点：
 * 		1. 浏览器地址栏路径不发生变化
 * 		2. 只能转发到当前服务器内部资源中。
 * 		3. 转发是一次请求
 *
 *
 * 3. 共享数据：
 * 	* 域对象：一个有作用范围的对象，可以在范围内共享数据
 * 	* request域：代表一次请求的范围，一般用于请求转发的多个资源中共享数据
 * 	* 方法：
 * 		1. void setAttribute(String name,Object obj):存储数据
 * 		2. Object getAttitude(String name):通过键获取值
 * 		3. void removeAttribute(String name):通过键移除键值对
 */
@WebServlet("/request5")
public class RequestDemo5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("request5被调用了");



        req.setAttribute("name","张三");
        req.setAttribute("age","18");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/request6");
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
