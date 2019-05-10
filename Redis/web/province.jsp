<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>全国省列表</title>

    <script src="js/jquery-3.3.1.min.js"></script>
    <!--
    案例需求：
    1. 提供index.html页面，页面中有一个省份 下拉列表
    2. 当 页面加载完成后 发送ajax请求，加载所有省份
        * 注意：使用redis缓存一些不经常发生变化的数据。
        * 数据库的数据一旦发生改变，则需要更新缓存。
        * 数据库的表执行 增删改的相关操作，需要将redis缓存数据情况，再次存入
        * 在service对应的增删改方法中，将redis数据删除。
    -->
    <script>
        $(function () {
           // 入口函数，加载完毕，发送ajax请求
           $.get("${pageContext.request.contextPath}/getProvinceServlet",null,function (data) {


               var ele_provinces = $("#provinces");
               //获取data中的所有值，遍历方式1
               for (var i = 0; i < data.length; i++) {
                   var option = "<option name='"+data[i]['id']+"'>"+data[i]['provinceName']+"</option>"
                   ele_provinces.append(option);
               }

               //遍历方式2:将其转成jq对象
               // $(data).each(function () {
               //     var option = "<option name='"+data[i]['id']+"'>"+data[i]['provinceName']+"</option>"
               //     ele_provinces.append(option);
               // })




           })
        });


    </script>

</head>
<body>

<select id="provinces">
    <option>--选择省--</option>
</select>

</body>
</html>