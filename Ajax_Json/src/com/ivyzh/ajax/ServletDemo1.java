package com.ivyzh.ajax;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//1. 概念： ASynchronous JavaScript And XML	异步的JavaScript 和 XML
//        1. 异步和同步：客户端和服务器端相互通信的基础上
//        * 客户端必须等待服务器端的响应。在等待的期间客户端不能做其他操作。
//        * 客户端不需要等待服务器端的响应。在服务器处理请求的过程中，客户端可以进行其他的操作。
//        Ajax 是一种在无需重新加载整个网页的情况下，能够更新部分网页的技术。 [1]
//        通过在后台与服务器进行少量数据交换，Ajax 可以使网页实现异步更新。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。
//        传统的网页（不使用 Ajax）如果需要更新内容，必须重载整个网页页面。
//        提升用户的体验


@WebServlet("/servletDemo1")
public class ServletDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("services.........");
        String username = req.getParameter("username");
        System.out.println("u:"+username);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("你好啊"+username);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
