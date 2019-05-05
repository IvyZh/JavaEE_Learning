<%@ page import="java.util.Date" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: Ivy
  Date: 2019/5/5
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>

<%
    Cookie[] cookies = request.getCookies();
    boolean isFirstTime = true;
    String lastTime = null;
    for (Cookie cookie : cookies) {
        System.out.println(cookie.getName()+"--"+cookie.getValue());
        String name = cookie.getName();
        if(name.equals("lastTime")){
            lastTime = cookie.getValue();
            isFirstTime = false;
            break;
        }
    }
    // 如果是首次登陆,创建cookie 返回
    if(isFirstTime){
        String time = new Date().toLocaleString();//2019-5-5 16:37:42
        System.out.println("time:"+time);
        //Cookie cookie = new Cookie("lastTime",time);// 注意不能存空格
        Cookie cookie = new Cookie("lastTime",URLEncoder.encode(time,"utf-8"));// 注意不能存空格
        cookie.setMaxAge(30 * 24 * 60 * 60);//一个月有效期
        response.addCookie(cookie);
        response.getWriter().write("您好，欢迎您首次访问");
    }else {//非首次登陆，则显示上次登陆时间
        response.getWriter().write("你上次登陆的时间为："+URLDecoder.decode(lastTime,"utf-8"));
    }




%>

</body>
</html>
