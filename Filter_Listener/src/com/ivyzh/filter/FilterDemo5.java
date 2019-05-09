package com.ivyzh.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


//* 拦截方式配置：资源被访问的方式
//        * 注解配置：
//        * 设置dispatcherTypes属性
//        1. REQUEST：默认值。浏览器直接请求资源
//        2. FORWARD：转发访问资源
//        3. INCLUDE：包含访问资源
//        4. ERROR：错误跳转资源
//        5. ASYNC：异步访问资源
//        * web.xml配置
//        * 设置<dispatcher></dispatcher>标签即可


//过滤器链(配置多个过滤器)
//        * 执行顺序：如果有两个过滤器：过滤器1和过滤器2
//        1. 过滤器1
//        2. 过滤器2
//        3. 资源执行
//        4. 过滤器2
//        5. 过滤器1
//
//        * 过滤器先后顺序问题：
//        1. 注解配置：按照类名的字符串比较规则比较，值小的先执行
//        * 如： AFilter 和 BFilter，AFilter就先执行了。
//        2. web.xml配置： <filter-mapping>谁定义在上边，谁先执行


@WebFilter(value = "/*",dispatcherTypes = DispatcherType.REQUEST)//默认值。浏览器直接请求资源
public class FilterDemo5 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo5 拦截了.....");
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
