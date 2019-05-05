<%--
  Created by IntelliJ IDEA.
  User: Ivy
  Date: 2019/5/5
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>

    <style>

        div{
            color: red;
        }

    </style>
</head>
<%--1. 案例需求：--%>
<%--1. 访问带有验证码的登录页面login.jsp--%>
<%--2. 用户输入用户名，密码以及验证码。--%>
<%--* 如果用户名和密码输入有误，跳转登录页面，提示:用户名或密码错误--%>
<%--* 如果验证码输入有误，跳转登录页面，提示：验证码错误--%>
<%--* 如果全部输入正确，则跳转到主页success.jsp，显示：用户名,欢迎您--%>



<body>

<div>登陆成功，欢迎回来！<%= request.getSession().getAttribute("user")%></div>

</body>


</html>
