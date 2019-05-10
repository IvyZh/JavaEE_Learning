package com.ivyzh.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkUserNameServlet")
public class CheckUserNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 还有一个问题没有暴露出来是因为tomcat8的，get请求会把中文乱码情况处理了！
        // 如果是tomcat7，则需要这段代码
//        byte[] bytes = req.getParameter("username").getBytes("iso8859-1");
//        String name = new String(bytes, "utf-8");


        String username = req.getParameter("username");
        System.out.println(username);
        Result result = new Result();
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");//在服务器端设置MIME类型,如果服务器不指定，则需要在客户端ajax请求的时候，写上json请求
        if(username!=null){
            if(username.equals("张三")&&username.equals("zhangsan")) {//用户名已经存在
                result.setFlag(false);
                result.setMsg("用户名已经存在");
            }else {
                result.setFlag(true);
                result.setMsg("用户名可以使用");
            }
        }else {
            result.setFlag(false);
            result.setMsg("用户名不能为空");
        }

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(result);
        resp.getWriter().write(s);




    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
