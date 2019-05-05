package com.ivyzh.login;

import com.ivyzh.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String valicodeUser = req.getParameter("valicode");

        Map<String, String[]> map = req.getParameterMap();
        User userLogin = new User();
        try {
            BeanUtils.populate(userLogin,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 简单模拟UserDao的操作
        System.out.println(userLogin);




        //String valicode = (String) this.getServletContext().getAttribute("valicode");
        String valicode = (String) req.getSession().getAttribute("valicode");


        String valicodeUser = userLogin.getValicode();
        System.out.println("用户输入valicodeUser:"+valicodeUser);
        System.out.println("系统生成valicode:"+valicode);


//        还有一个问题是，当登陆成功之后，点击返回按钮，发现验证码没有更新，还是上一次的。
//        解决：获取完，立马删除！
        req.getSession().removeAttribute("valicode");

//
        if(valicodeUser.equalsIgnoreCase(valicode)){
            System.out.println("验证码正确");
            // 检验用户名和密码
            if(userLogin.getUsername().equals("zhangsan")&&userLogin.getPassword().equals("123")){//这里简单模拟UserDao
               // 具体可以参考tomcat_servlet里面的登陆
                System.out.println("登陆成功");
                // 登陆成功，使用重定向到success页面,使用session存储用户信息
                User user = new User();
                user.setUsername("张三");
                user.setNickname("zs");
                req.getSession().setAttribute("user",user);
                resp.sendRedirect(req.getContextPath()+"/succes.jsp");


            }else {
                System.out.println("用户名或密码错误");
                req.setAttribute("user_p_error","用户名或密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }


        }else {
            System.out.println("验证码错误");
            // 如果是验证码错误，可以用转发,此时就可以用request域共享数据
            req.setAttribute("valicode_error","验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);

        }



        /*
        本想用BeanUtils的封装包的，但是依旧报错：
        // 问题已解决：导包导入两个，位置在WEB-INF/lib目录下面：commons-beanutils-1.8.0.jar 和 commons-logging-1.2 缺一不可！
        // 同时add lib的时候选择，moudule
        java.lang.NoClassDefFoundError: org/apache/commons/beanutils/BeanUtils
        com.ivyzh.login.LoginServlet.doPost(LoginServlet.java:21)
        javax.servlet.http.HttpServlet.service(HttpServlet.java:661)
        javax.servlet.http.HttpServlet.service(HttpServlet.java:742)
        org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
*/




    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
