<%--
  Created by IntelliJ IDEA.
  User: Ivy
  Date: 2019/5/5
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JspDemo1</title>
</head>
<body>

<%--* Java Server Pages： java服务器端页面--%>
<%--* 可以理解为：一个特殊的页面，其中既可以指定定义html标签，又可以定义java代码--%>
<%--* 用于简化书写！！！JSP本质上就是一个Servlet--%>


<%--3. JSP的脚本：JSP定义Java代码的方式--%>
<%--1. <%  代码 %>：定义的java代码，在service方法中。service方法中可以定义什么，该脚本中就可以定义什么。--%>
<%--2. <%! 代码 %>：定义的java代码，在jsp转换后的java类的成员位置。用的比较少--%>
<%--3. <%= 代码 %>：定义的java代码，会输出到页面上。输出语句中可以定义什么，该脚本中就可以定义什么。--%>


<%--Java代码 在控制台输出语句--%>
<% System.out.println("hello jsp");%>

<h1>hi jsp!</h1>

</body>
</html>
