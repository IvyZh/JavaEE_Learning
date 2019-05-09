<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>EL 1</title>
  </head>
  <body>
  <!--
    1. 概念：Expression Language 表达式语言
    2. 作用：替换和简化jsp页面中java代码的编写
    3. 语法：${表达式}
    4. 注意：
    * jsp默认支持el表达式的。如果要忽略el表达式
    1. 设置jsp中page指令中：isELIgnored="true" 忽略当前jsp页面中所有的el表达式
    2. \${表达式} ：忽略当前这个el表达式
  -->
  <!--
  EL_运算符：
  1. 算数运算符： + - * /(div) %(mod)
  2. 比较运算符： > < >= <= == !=
  3. 逻辑运算符： &&(and) ||(or) !(not)
  4. 空运算符： empty
  * 功能：用于判断字符串、集合、数组对象是否为null或者长度是否为0
  * ${empty list}:判断字符串、集合、数组对象是否为null或者长度为0
  * ${not empty str}:表示判断字符串、集合、数组对象是否不为null 并且 长度>0
    -->


  <h1>算术运算符</h1>
  ${10 +3} <br>
  ${10 -3} <br>
  ${10 *3} <br>
  ${10 /3} <br>
  ${10 %3} <br>

  <h1>比较运算符</h1>
  ${3>4} <br>
  ${3<4}<br>
  ${3<=4}<br>
  ${3>=4}<br>
  ${3==4}<br>
  ${3!=4}<br>


  <h1>逻辑运算符</h1>
  ${3>4 && 3<4} <br>
  ${3>4 and 3<4} <br>
  ${3>4 || 3<4} <br>
  ${3>4 or 3<4} <br>
  ${!(3>4)}<br>
  ${not(3>4)}

  <h1>空运算符</h1>
<%
  ArrayList<Object> list1 = new ArrayList<>();
  String str1 = "";
  String str2 = null;
%>
  ${empty list1}
  ${empty str1}
  ${empty str2}


  </body>
</html>
