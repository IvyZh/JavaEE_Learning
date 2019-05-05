package com.ivyzh.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 4. cookie的获取范围有多大？cookie共享问题？
 * 什么叫cookie的获取范围？
 * 	1. 假设在一个tomcat服务器中，部署了多个web项目，那么在这些web项目中cookie能不能共享？
 * 	* 默认情况下cookie不能共享
 * 	* setPath(String path):设置cookie的获取范围。默认情况下，设置当前的虚拟目录
 * 			* 如果要共享，则可以将path设置为"/"
 * 	2. 不同的tomcat服务器间cookie共享问题？
 * 	* setDomain(String path):如果设置一级域名相同，那么多个服务器之间cookie可以共享
 * 		* setDomain(".baidu.com"),那么tieba.baidu.com和news.baidu.com中cookie可以共享
 */
@WebServlet("/cookieDemo3")
public class CookieDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CookieDemo3....");
        Cookie cookie = new Cookie("cookieInCs","cookie共享");
        cookie.setMaxAge(5*60);//5分钟
        cookie.setPath("/");//设置path，让当前服务器部署的所有项目共享cookie信息
        resp.addCookie(cookie);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
