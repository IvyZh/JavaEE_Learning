<%@ page import="com.ivyzh.el.domain.Person" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>EL 2</title>
  </head>
  <body>

  <!--
  EL_获取域中存储的值：
 	1. el表达式只能从域对象中获取值
	2. 语法：
		1. ${域名称.键名}：从指定域中获取指定键的值
			* 域名称：
				1. pageScope		  pageContext
                2. requestScope 	  request
                3. sessionScope 	  session
                4. applicationScope   application（ServletContext）
                * 举例：在request域中存储了name=张三
                * 获取：${requestScope.name}
        2. ${键名}：表示依次从最小的域中查找是否有该键对应的值，直到找到为止。
        3. 获取对象、List集合、Map集合的值
        1. 对象：${域名称.键名.属性名}
        * 本质上会去调用对象的getter方法
        2. List集合：${域名称.键名[索引]}
        3. Map集合：
        * ${域名称.键名.key名称}
        * ${域名称.键名["key名称"]}
-->
<%
  pageContext.setAttribute("pageContext_data","AA");
  request.setAttribute("request_data","BB");
  session.setAttribute("session_data","CC");
  application.setAttribute("application_data","DD");


  pageContext.setAttribute("data","张三");
  request.setAttribute("data","李四");
  session.setAttribute("data","王五");
  application.setAttribute("data","赵六");

%>

  <h1>从指定域中获取指定键的值</h1>
  pageContext:${pageScope.pageContext_data} <br>
  request:${requestScope.request_data} <br>
  session:${sessionScope.session_data} <br>
  application:${applicationScope.application_data} <br>
  <h1>从指定域中获取指定键的值，不带域名称</h1>
  data:${data}   张三


  <h1>EL_获取域中存储的值_对象值</h1>
  <%

    Person person = new Person();
    person.setBirthDay(new Date());
    person.setName("zhangsan");
    person.setGender(0);
    request.setAttribute("p1",person);
  %>

  <%--本质上会去调用对象的getter方法--%>
  p1 name:${p1.name} <br>
  p1 gender:${p1.gender}<br>
  p1 birthDay:${p1.birthDay}<br>
  p1 birthDayFormat:${p1.birthDayFormat}<br>


  <h1>EL_获取域中存储的值_List</h1>


  <%

    ArrayList list1 = new ArrayList();
    list1.add("ABC");
    list1.add(888);
    list1.add(person);
    request.setAttribute("list1",list1);
  %>

  ${list1[0]}<br>
  ${list1[1]}<br>
  ${list1[2]}<br>
  ${list1[3]}<br>
  <%--越界也不会报错--%>
  ${list1[2].name}<br>

  <h1>EL_隐式对象pageContext</h1>
  <%--3. 隐式对象：--%>
  <%--* el表达式中有11个隐式对象--%>
  <%--* pageContext：--%>
  <%--* 获取jsp其他八个内置对象--%>
  <%--* ${pageContext.request.contextPath}：动态获取虚拟目录--%>
  虚拟目录：${pageContext.request.contextPath}

  </body>
</html>
