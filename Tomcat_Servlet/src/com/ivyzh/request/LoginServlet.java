package com.ivyzh.request;

import com.ivyzh.dao.UserDao;
import com.ivyzh.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("登陆请求来了...");
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+","+password);
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);


        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        if(user==null){//登陆失败,跳转到fail的Servlet
            req.getRequestDispatcher("/loginFail").forward(req,resp);
        }else {//登陆成功，跳转到success页面
            req.setAttribute("user",user);
            req.getRequestDispatcher("/loginSuccess").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
