package com.ivyzh.usercrm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Map;

@WebFilter("/*")
public class SensitiveFilter implements Filter {
    ArrayList<String> words;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest hReq = (HttpServletRequest) req;
        String uri = hReq.getRequestURI();
        if(uri.contains("updateUserServlet")){//更新的时候拦截
            System.out.println("拦截更新操作.....");
            ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (method.getName().equals("getParameter")) {
                        String value = (String) method.invoke(req, args);
                        if(value!=null){
                            for(String w:words){
                                if(value.contains(w)){
                                    value = value.replaceAll(w,"NULL");
                                }
                            }
                        }

                        return value;

                    }
                    return method.invoke(req, args);
                }
            });

            chain.doFilter(proxy_req, resp);

        }else {
            chain.doFilter(req, resp);
        }



        /* 同样的操作方法map为什么不能修改呢？？？？？？
        if(uri.contains("updateUserServlet")){//更新的时候拦截
            System.out.println("拦截更新操作.....");


            ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (method.getName().equals("getParameterMap")) {
                        Map<String, String[]> map = (Map<String, String[]>) method.invoke(req, args);
                        if (map != null) {
                            String[] usernames = map.get("username");
                            if (usernames != null && usernames[0] != null) {
                                for (String w : words) {
                                    System.out.println("w:" + w);
                                    if (usernames[0].contains(w)) {
                                        System.out.println("replace...");
                                        String v = usernames[0].replaceAll(w, "NULL");
                                        map.put("username", new String[]{"0000000000"});
                                    }
                                }

                            }
                            return map;
                        }
                        return method.invoke(req, args);

                    }
                    return method.invoke(req, args);
                }
            });

            chain.doFilter(proxy_req, resp);

        }else {
            chain.doFilter(req, resp);
        }

    */
    }

    // 初始化敏感词汇
    public void init(FilterConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/sensitive.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(realPath));
            String line = null;
            words = new ArrayList<>();
            while ((line =br.readLine())!=null){
                words.add(line);
                System.out.println("add w:"+line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
