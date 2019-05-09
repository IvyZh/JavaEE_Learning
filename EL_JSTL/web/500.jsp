<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
  <head>
    <title>错误页面</title>
  </head>
  <body>
      服务器异常...
      <%
      String error = exception.getMessage();
      out.print(error);
      %>


      <%--page		： 配置JSP页面的--%>
      <%--* contentType：等同于response.setContentType()--%>
      <%--1. 设置响应体的mime类型以及字符集--%>
      <%--2. 设置当前jsp页面的编码（只能是高级的IDE才能生效，如果使用低级工具，则需要设置pageEncoding属性设置当前页面的字符集）--%>
      <%--* import：导包--%>
      <%--* errorPage：当前页面发生异常后，会自动跳转到指定的错误页面--%>
      <%--* isErrorPage：标识当前也是是否是错误页面。--%>
      <%--* true：是，可以使用内置对象exception--%>
      <%--* false：否。默认值。不可以使用内置对象exception--%>

  </body>
</html>
