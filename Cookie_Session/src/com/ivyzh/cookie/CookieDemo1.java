package com.ivyzh.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieDemo1")
public class CookieDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CookieDemo1....");
        Cookie cookie = new Cookie("name","张三");
        Cookie cookie2 = new Cookie("age","18");
        Cookie cookie3 = new Cookie("gender","male");

//        cookie在浏览器中保存多长时间？
//        1. 默认情况下，当浏览器关闭后，Cookie数据被销毁
//        2. 持久化存储：
//		* setMaxAge(int seconds)
//        1. 正数：将Cookie数据写到硬盘的文件中。持久化存储。并指定cookie存活时间，时间到后，cookie文件自动失效
//        2. 负数：默认值
//        3. 零：删除cookie信息

        cookie.setMaxAge(30);//30秒

        resp.addCookie(cookie);
        resp.addCookie(cookie2);
        resp.addCookie(cookie3);



    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
