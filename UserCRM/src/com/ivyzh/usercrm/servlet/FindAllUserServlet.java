package com.ivyzh.usercrm.servlet;

import com.ivyzh.usercrm.domain.PageBean;
import com.ivyzh.usercrm.domain.User;
import com.ivyzh.usercrm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/findAllUserServlet")
public class FindAllUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        System.out.println("FindAllUserServlet....");
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");

        String username = req.getParameter("username");
        String address = req.getParameter("address");
        String email = req.getParameter("email");

        System.out.println(username+address+email);

        Map<String, String[]> condition = req.getParameterMap();
        Set<String> set = condition.keySet();
        for (String s : set) {
            System.out.println(s+"--"+condition.get(s)[0]);
        }

        if(currentPage==null||currentPage.length()==0){
            currentPage = "1";
        }
        if(pageSize==null||pageSize.length()==0){
            pageSize = "5";
        }
        UserServiceImpl service = new UserServiceImpl();
        PageBean<User> page = service.findAllUser(currentPage,pageSize,condition);
        System.out.println(page);
        req.setAttribute("page",page);
        // 查询条件回显
        req.setAttribute("condition",condition);
        req.getRequestDispatcher("/list.jsp").forward(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
