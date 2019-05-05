package com.ivyzh.dao;

import com.ivyzh.domain.User;

public class UserDao {


    //    不知道为什么这个类会报错。先暂时放一下，报错信息

    /*
    Message Servlet execution threw an exception

    Description The server encountered an unexpected condition that prevented it from fulfilling the request.

    Exception

    javax.servlet.ServletException: Servlet execution threw an exception
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)

    Root Cause

    java.lang.NoClassDefFoundError: org/springframework/jdbc/core/RowMapper
	com.ivyzh.request.LoginServlet.doGet(LoginServlet.java:27)
            com.ivyzh.request.LoginServlet.doPost(LoginServlet.java:39)
            javax.servlet.http.HttpServlet.service(HttpServlet.java:661)
            javax.servlet.http.HttpServlet.service(HttpServlet.java:742)
            org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)

    Root Cause

    java.lang.ClassNotFoundException: org.springframework.jdbc.core.RowMapper
	org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1291)
            org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1119)
            com.ivyzh.request.LoginServlet.doGet(LoginServlet.java:27)
            com.ivyzh.request.LoginServlet.doPost(LoginServlet.java:39)
            javax.servlet.http.HttpServlet.service(HttpServlet.java:661)
            javax.servlet.http.HttpServlet.service(HttpServlet.java:742)
            org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)

    */


//    //声明JDBCTemplate对象共用
//    private JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
//    public User login(User user){
//        System.out.println("UserDao login...");
//        String sql = "select * from user where username = ? and password = ?";
//        User u = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
//        System.out.println("u:"+u);
//        return u;
//    }

        public User login(User user){
            System.out.println("UserDao login...");
            String sql = "select * from user where username = ? and password = ?";
            User u = new User();
            u.setUsername("admin");
            u.setPassword("123");
            u.setNickname("ad");
            return u;
         }





    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User u = new User();
        u.setUsername("zhangsan");
        u.setPassword("123");
        User login = userDao.login(u);
        System.out.println(login);
    }
}
