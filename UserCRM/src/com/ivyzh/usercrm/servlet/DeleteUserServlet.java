package com.ivyzh.usercrm.servlet;

import com.ivyzh.usercrm.domain.User;
import com.ivyzh.usercrm.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("deleteUserServlet....");
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        if(id!=null&&id.length()>0){
            int idInt = Integer.parseInt(id);
            UserServiceImpl userService = new UserServiceImpl();
            boolean delete = userService.delete(idInt);
            if(delete){

            }else {

            }
            resp.sendRedirect(req.getContextPath()+"/findAllUserServlet");
        }else {
            System.out.println("参数不正确");
            resp.sendRedirect(req.getContextPath()+"/findAllUserServlet");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
