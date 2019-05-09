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

<%--action,应该用EL表达式的pageContex.request.contextPath：来获取虚拟目录--%>
<form action="/cs/login" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"></td>

        </tr>

        <tr>
            <td>密码：</td>
            <td><input type="password" name="password"></td>

        </tr>

        <tr>
            <td>验证码：</td>
            <td>
                <input type="text" name="valicode">

                <img src="/cs/valicode" id="img_check">

            </td>

        </tr>
        <tr>
            <td><input type="submit" value="登陆"></td>
        </tr>


    </table>

</form>

<%--<div><%= request.getAttribute("valicode_error") ==null?"":request.getAttribute("valicode_error")%></div>
<div><%= request.getAttribute("user_p_error") ==null?"":request.getAttribute("user_p_error")%></div>--%>

<%--改造之前的登陆代码，使用EL表达式--%>
<div>${requestScope.valicode_error}></div>
<div>${requestScope.user_p_error}></div>



</body>

<script>

    window.onload = function (ev) {
        var img_check = document.getElementById("img_check");
        img_check.onclick = function (ev1) {
            img_check.src = "/cs/valicode?time="+new Date().getTime();
        }
    }


</script>
</html>
