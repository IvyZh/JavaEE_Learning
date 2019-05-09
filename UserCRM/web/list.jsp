<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        function deleteUser(id) {
            if(confirm("确定删除吗？")){
                location.href = "${pageContext.request.contextPath}/deleteUserServlet?id="+id;
            }
        }

        window.onload = function (ev) {
            var selectAll_ele = document.getElementById("selectAll");
            var del_ele = document.getElementById("delSeleted");
            del_ele.onclick = function (ev1) {

                var cbs = document.getElementsByName("uid");
                var selected = false;
                for (var i = 0;i<cbs.length;i++){
                    if(cbs[i].checked){
                        selected = true;
                        break;
                    }

                }
                if(!selected){
                    alert("请先选中删除项！");
                    return;
                }



                if(confirm("确定删除选中吗？")){
                    document.getElementById("form").submit();//提交表单
                }
            }

            selectAll_ele.onclick = function (ev1) {

                var cbs = document.getElementsByName("uid");
                for (var i = 0;i<cbs.length;i++){
                    cbs[i].checked = this.checked;
                }
            }



        }

    </script>
</head>
<body>
<div class="container">

    <div>${user.username}，欢迎回来！</div>

    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left">
        <form class="form-inline" action="${pageContext.request.contextPath}/findAllUserServlet" method="post">
            <div class="form-group">
                <label for="username">姓名</label>
                <input type="text" class="form-control" id="username" name="username" value="${condition.username[0]}" >
            </div>
            <div class="form-group">
                <label for="address">籍贯</label>
                <input type="text" class="form-control" id="address"  name="address" value="${condition.address[0]}">
            </div>
            <div class="form-group">
                <label for="email">邮箱</label>
                <input type="text" class="form-control" id="email"  name="email" value="${condition.email[0]}">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <div colspan="8" style="float: right;margin: 8px"  ><a class="btn btn-primary" href="javascript:void(0)" id="delSeleted">删除选中</a></div>
    <div colspan="8" style="float: right;margin: 8px"  ><a class="btn btn-primary" href="add.jsp">添加联系人</a></div>
    <form id="form" action="${pageContext.request.contextPath}/deleteSelectedUserServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="selectAll"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>

            <c:if test="${not empty page.list}">
                <c:forEach items="${page.list}" var="user" varStatus="s" >
                    <tr>
                        <td><input type="checkbox" name="uid" value="${user.id}"></td>
                        <td>${s.count}</td>
                        <td>${user.username}</td>
                        <c:if test="${user.gender ==0}">
                            <td>男</td>
                        </c:if>
                        <c:if test="${user.gender ==1}">
                            <td>女</td>
                        </c:if>
                        <td>${user.age}</td>
                        <td>${user.address}</td>
                        <td>${user.QQ}</td>
                        <td>${user.email}</td>
                        <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id})">删除</a></td>
                    </tr>
                </c:forEach>
            </c:if>

        </table>

    </form>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${page.currentPage==1}">
               <%--这个禁用效果无效，可能是没引入完整的bootstrap包--%>
                <li class="">
                    <a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=1&pageSize=${page.pageSize}&username=${condition.username[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>

            <c:if test="${page.currentPage!=1}">
                <li>
                    <a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=${page.currentPage-1}&pageSize=${page.pageSize}&username=${condition.username[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>

            <%--<li><a href="#">1</a></li>--%>

            <c:forEach begin="1" end="${page.totalPage}" var="i">

                <c:if test="${page.currentPage==i}">
                    <li class="active"><a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=${i}&pageSize=${page.pageSize}&username=${condition.username[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                </c:if>

                <c:if test="${page.currentPage!=i}">
                    <li ><a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=${i}&pageSize=${page.pageSize}&username=${condition.username[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                </c:if>

            </c:forEach>



            <c:if test="${page.currentPage==page.totalPage}">
                <%--这个禁用效果无效，可能是没引入完整的bootstrap包--%>
                <li >
                    <a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=${page.currentPage}&pageSize=${page.pageSize}&username=${condition.username[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${page.currentPage!=page.totalPage}">
                <%--这个禁用效果无效，可能是没引入完整的bootstrap包--%>
                <li >
                    <a href="${pageContext.request.contextPath}/findAllUserServlet?currentPage=${page.currentPage+1}&pageSize=${page.pageSize}&username=${condition.username[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <span style="font-size: 20px;margin-left: 10px">共<span id="totalCount">${page.totalCount}</span>条记录，共<span id="totalPage">${page.totalPage}</span>页</span>
        </ul>


    </nav>

</div>

</body>
</html>
