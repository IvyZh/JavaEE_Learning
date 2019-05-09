package com.ivyzh.usercrm.servlet;

import com.ivyzh.usercrm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSelectedUserServlet")
public class DeleteSeletedUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("deleteSelectedUserServlet....");
        req.setCharacterEncoding("utf-8");
        String[] uids = req.getParameterValues("uid");
        for (String uid : uids) {
            System.out.println(uid);
        }
        UserServiceImpl userService = new UserServiceImpl();
        userService.deleteSeletedUser(uids);
        resp.sendRedirect(req.getContextPath()+"/findAllUserServlet");//给客户端用的要加虚拟路径！

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
