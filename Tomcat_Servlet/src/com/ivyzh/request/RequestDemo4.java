package com.ivyzh.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/request4")
public class RequestDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        req.setCharacterEncoding("utf-8");

        // 获取请求参数通用方式演示
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String method = req.getMethod();
        System.out.println(method+"--"+username+"--"+password);
        // POST--zs--12345
        // POST--??????--123
        // GET--zs--123
        // GET--张三--123
        // 中文乱码问题:get方式：tomcat 8 已经将get方式乱码问题解决了
        //  post方式：会乱码,解决：在获取参数前，设置request的编码request.setCharacterEncoding("utf-8");

        String[] hobbies = req.getParameterValues("hobby");
        System.out.println("hobby:"+hobbies);

        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println("parameterMap:"+parameterMap);
        Enumeration<String> parameterNames = req.getParameterNames();
        System.out.println("parameterNames:"+parameterNames);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
