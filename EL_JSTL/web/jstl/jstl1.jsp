<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>JSTL 1</title>
  </head>
  <body>
  <%--4. 常用的JSTL标签--%>
  <%--1. if:相当于java代码的if语句--%>
    <%--1. 属性：--%>
    <%--* test 必须属性，接受boolean表达式--%>
    <%--* 如果表达式为true，则显示if标签体内容，如果为false，则不显示标签体内容--%>
    <%--* 一般情况下，test属性值会结合el表达式一起使用--%>
    <%--2. 注意：--%>
    <%--* c:if标签没有else情况，想要else情况，则可以在定义一个c:if标签--%>
  <%--2. choose:相当于java代码的switch语句--%>
    <%--1. 使用choose标签声明         			相当于switch声明--%>
    <%--2. 使用when标签做判断         			相当于case--%>
    <%--3. 使用otherwise标签做其他情况的声明    	相当于default--%>
  <%--3. foreach:相当于java代码的for语句--%>

<%
  int num = 9;
  ArrayList list1 = new ArrayList();
  list1.add("AA");
  list1.add("BB");
  list1.add("CC");

  list1 = null;
  request.setAttribute("num",num);
  request.setAttribute("list1",list1);

//  判断今天星期几
  int day = 15;
  request.setAttribute("day",day);


%>



  <h1>c:if练习</h1>
   <%--判断num的奇偶性--%>
  <c:if test="${num%2==0}">
    num的值为偶数 ${num}
  </c:if>
  <c:if test="${num%2!=0}">
    num的值为奇数 ${num}
  </c:if>

  <%--判断request域中的一个list集合是否为空，不为空则遍历输出--%>
  <c:if test="${not empty list1}">
    list 不为空
  </c:if>
  <c:if test="${ empty list1}">
    list 为空
  </c:if>

  <h1>c:choose练习</h1>

  <c:choose>
    <c:when test="${day==1}">星期一</c:when>
    <c:when test="${day==2}">星期二</c:when>
    <c:when test="${day==3}">星期三</c:when>
    <c:when test="${day==4}">星期四</c:when>
    <c:when test="${day==5}">星期五</c:when>
    <c:when test="${day==6}">星期六</c:when>
    <c:when test="${day==7}">星期日</c:when>
    <c:otherwise>输入有误！！</c:otherwise>


  </c:choose>
  <h1>c:forEach练习</h1>
  <%
    ArrayList list2 = new ArrayList();
    list2.add("AA");
    list2.add("BB");
    list2.add("CC");
    request.setAttribute("list2",list2);

  %>
  <c:forEach items="${list2}" var="str" varStatus="s">
    ${s.index}
    ${s.count}
    ${str}
<br>

  </c:forEach>



  </body>
</html>
