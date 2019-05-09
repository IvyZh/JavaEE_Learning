<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ivyzh.el.domain.User" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>JSTL 练习</title>

    <style>
      tr {
        align:center
      }
    </style>
  </head>
  <body>
  <%--需求：在request域中有一个存有User对象的List集合。需要使用jstl+el将list集合数据展示到jsp页面的表格table中--%>

<%
  ArrayList<User> users = new ArrayList<>();
  users.add(new User("张三",18,new Date()));
  users.add(new User("李四",27,new Date()));
  users.add(new User("王五",22,new Date()));
  users.add(new User("Mike",22,new Date()));
  users.add(new User("Sam",22,new Date()));
  users.add(new User("King",22,new Date()));
  users.add(new User("Tom",22,new Date()));
  request.setAttribute("users",users);
%>

  <table border="1" cellspacing="0" cellpadding="0" align="center" width="500">
    <tr>
      <th>编号</th>
      <th>姓名</th>
      <th>年龄</th>
      <th>生日</th>
    </tr>

    <c:if test="${not empty users}">

      <c:forEach items="${users}" varStatus="s" var="user">

        <c:if test="${s.index%2==0}">
          <tr align="center" bgcolor="#ffc0cb">
            <td>${s.count}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.birthDayStr}</td>
          </tr>
        </c:if>

        <c:if test="${s.index%2!=0}">
          <tr align="center" bgcolor="gray">
            <td>${s.count}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.birthDayStr}</td>
          </tr>
        </c:if>


      </c:forEach>
    </c:if>



  </table>

  </body>
</html>
