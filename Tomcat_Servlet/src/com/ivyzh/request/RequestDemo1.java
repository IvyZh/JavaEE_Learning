package com.ivyzh.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/request")
public class RequestDemo1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get...");
        // 1. 获取请求行数据
        String method = req.getMethod();
        String contextPath = req.getContextPath();
        String queryString = req.getQueryString();
        String requestURI = req.getRequestURI();
        StringBuffer requestURL = req.getRequestURL();
        String remoteAddr = req.getRemoteAddr();

        System.out.println(method+","+contextPath+","+queryString+","+requestURI+","+requestURL+","+remoteAddr);
        // http://localhost/demo/request?name=zhangsan&gender=male
        // GET,/demo,name=zhangsan&gender=male,/demo/request,http://localhost/demo/request,0:0:0:0:0:0:0:1
        System.out.println("-------------------");
        // 2. 获取请求头数据
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = req.getHeader(name);
            System.out.println(name+"---"+value);
        }

        String header = req.getHeader("user-agent");
        if(header.contains("Chrome")){
            System.out.println("Chrome浏览器访问");
        }else if(header.contains("Firefox")){
            System.out.println("Firefox浏览器访问");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
