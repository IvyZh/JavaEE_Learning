package com.ivyzh.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 6. 案例：记住上一次访问时间
 * 	1. 需求：
 * 		1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
 * 		2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串
 * 	2. 分析：
 * 		1. 可以采用Cookie来完成
 * 		2. 在服务器中的Servlet判断是否有一个名为lastTime的cookie
 * 			1. 有：不是第一次访问
 * 				1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
 * 				2. 写回Cookie：lastTime=2018年6月10日11:50:01
 * 			2. 没有：是第一次访问
 * 				1. 响应数据：您好，欢迎您首次访问
 * 				2. 写回Cookie：lastTime=2018年6月10日11:50:01
 */
@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("utf-8");
        System.out.println("案例：记住上一次访问时间");
        Cookie[] cookies = req.getCookies();
        boolean isFirstTime = true;
        String lastTime = null;
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName()+"--"+cookie.getValue());
            String name = cookie.getName();
            if(name.equals("lastTime")){
                lastTime = cookie.getValue();
                isFirstTime = false;
                break;
            }
        }
        // 如果是首次登陆,创建cookie 返回
        if(isFirstTime){
            String time = new Date().toLocaleString();//2019-5-5 16:37:42
            System.out.println("time:"+time);
            //Cookie cookie = new Cookie("lastTime",time);// 注意不能存空格
            Cookie cookie = new Cookie("lastTime",URLEncoder.encode(time,"utf-8"));// 注意不能存空格
            cookie.setMaxAge(30 * 24 * 60 * 60);//一个月有效期
            resp.addCookie(cookie);
            resp.getWriter().write("您好，欢迎您首次访问");
        }else {//非首次登陆，则显示上次登陆时间
            resp.getWriter().write("你上次登陆的时间为："+URLDecoder.decode(lastTime,"utf-8"));
        }


    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
