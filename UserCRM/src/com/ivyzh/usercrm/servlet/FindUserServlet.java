package com.ivyzh.usercrm.servlet;

import com.ivyzh.usercrm.domain.User;
import com.ivyzh.usercrm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("findUserServlet....");
        UserServiceImpl service = new UserServiceImpl();
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);
        User user = service.findUserById(id);
        System.out.println(user);
        req.setAttribute("user",user);
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
