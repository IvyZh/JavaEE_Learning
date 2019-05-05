package com.ivyzh.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 1. 概念：服务器端会话技术，在一次会话的多次请求间共享数据，将数据保存在服务器端的对象中。HttpSession
 * 2. 快速入门：
 * 	1. 获取HttpSession对象：
 * 		HttpSession session = request.getSession();
 * 	2. 使用HttpSession对象：
 * 		Object getAttribute(String name)
 * 		void setAttribute(String name, Object value)
 * 		void removeAttribute(String name)
 * 3. 原理
 * 	* Session的实现是依赖于Cookie的。
 */
@WebServlet("/sessionDemo1")
public class SessionDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        1. 当客户端关闭后，服务器不关闭，两次获取session是否为同一个？
//        * 默认情况下。不是。
//        * 如果需要相同，则可以创建Cookie,键为JSESSIONID，设置最大存活时间，让cookie持久化保存。
//        Cookie c = new Cookie("JSESSIONID",session.getId());
//        c.setMaxAge(60*60);
//        response.addCookie(c);


        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();

        Cookie jsessionid = new Cookie("JSESSIONID", session.getId());
        jsessionid.setMaxAge(10*60);//60s
        resp.addCookie(jsessionid);

        session.setAttribute("name","李四");
        session.setAttribute("age","28");
        session.setAttribute("gender","female");
        resp.getWriter().write("存储session...");

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
