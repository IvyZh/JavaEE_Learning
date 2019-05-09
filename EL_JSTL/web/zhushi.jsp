<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>注释</title>
  </head>
  <body>

  1. html注释：只能注释html代码片段，注释之后在查看源码的时候，会看到
  <br>
  <!-- html注释-->
  2. jsp注释：推荐使用，可以注释所有，包括html。注释HTML之后在查看源码的时候，不会看到。
  <%-- jsp注释<h1>哈</h1>--%>


  <div>

    * 在jsp页面中不需要创建，直接使用的对象
    * 一共有9个：
    变量名			真实类型					作用
    * pageContext		PageContext				当前页面共享数据，还可以获取其他八个内置对象
    * request			HttpServletRequest			一次请求访问的多个资源(转发)
    * session			HttpSession				一次会话的多个请求间
    * application		ServletContext			所有用户间共享数据
    * response		HttpServletResponse		响应对象
    * page			Object					当前页面(Servlet)的对象  this
    * out				JspWriter					输出对象，数据输出到页面上
    * config			ServletConfig				Servlet的配置对象
    * exception		Throwable				异常对象

  </div>>
 <%-- 1. jsp演变历史
  1. 早期只有servlet，只能使用response输出标签数据，非常麻烦
  2. 后来有了jsp，简化了Servlet的开发，如果过度使用jsp，在jsp中即写大量的java代码，有写html表，造成难于维护，难于分工协作
  3. 再后来，java的web开发，借鉴mvc开发模式，使得程序的设计更加合理性--%>
  
  </body>
</html>
