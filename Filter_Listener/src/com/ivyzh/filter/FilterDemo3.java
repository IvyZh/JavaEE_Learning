package com.ivyzh.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


//2. 过滤器执行流程
//        1. 执行过滤器
//        2. 执行放行后的资源
//        3. 回来执行过滤器放行代码下边的代码
//        3. 过滤器生命周期方法
//        1. init:在服务器启动后，会创建Filter对象，然后调用init方法。只执行一次。用于加载资源
//        2. doFilter:每一次请求被拦截资源时，会执行。执行多次
//        3. destroy:在服务器关闭后，Filter对象被销毁。如果服务器是正常关闭，则会执行destroy方法。只执行一次。用于释放资源

//@WebFilter("/*")
public class FilterDemo3 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterDemo3 before....");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("FilterDemo3 after....");

    }

    @Override
    public void destroy() {
        System.out.println("destroy....");

    }
}
