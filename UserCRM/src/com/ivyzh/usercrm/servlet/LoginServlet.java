package com.ivyzh.usercrm.servlet;

import com.ivyzh.usercrm.domain.User;
import com.ivyzh.usercrm.service.UserService;
import com.ivyzh.usercrm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login....");
        req.setCharacterEncoding("utf-8");
        String verifycode = req.getParameter("verifycode");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+"--"+password);

        String checkcode_server = (String) req.getSession().getAttribute("CHECKCODE_SERVER");
        req.getSession().removeAttribute("CHECKCODE_SERVER");


        if(verifycode!=null && verifycode.equalsIgnoreCase(checkcode_server)){
            System.out.println("检验用户名和密码");
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            UserService userService = new UserServiceImpl();
            User login = userService.login(user);
            if(login!=null){//登陆成功
                req.getSession().setAttribute("user",login);//保存用户信息，跳转到用户列表
                resp.sendRedirect(req.getContextPath()+"/index.jsp");
            }else {
                //重定向到login.jsp页面
                req.setAttribute("msg","用户名或密码不正确");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }


        }else {
            //重定向到login.jsp页面
            req.setAttribute("msg","验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
