package com.ivyzh.usercrm.filter;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String uri = httpRequest.getRequestURI();
        System.out.println("uri-------->"+uri);
//        uri-------->/user/login.jsp
//        uri-------->/user/valiCodeServlet
        //login.jsp
        if(uri.contains("loginServlet")||uri.contains("valiCodeServlet")||uri.contains("login.jsp")
                ||uri.contains("/css/")
                ||uri.contains("/js/")
                ||uri.contains("/fonts/")
                ){
            chain.doFilter(req, resp);
        }else {
            Object user = httpRequest.getSession().getAttribute("user");
            if(user!=null){
                chain.doFilter(req, resp);//已经登录，放行

            }else {//未登录，拦截提示

                chain.doFilter(req, resp);//已经登录，放行

//                httpRequest.setAttribute("msg","您尚未登录，请登录");
//                httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest,resp);
            }
        }




    }

    public void init(FilterConfig config) throws ServletException {

    }

}
