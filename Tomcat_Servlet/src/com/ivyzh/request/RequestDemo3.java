package com.ivyzh.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/request3")
public class RequestDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求体数据
        System.out.println("获取请求体数据");

        BufferedReader reader = req.getReader();
        String line=null;
        while ((line=reader.readLine())!=null){
            System.out.println(line);
            //username=zs&password=123456&email=1%40163.com&gender=male&hobby=read&portrait=&birthday=2019-05-07&location=sh&intro=ssss&checkcode=sss
        }


    }
}
