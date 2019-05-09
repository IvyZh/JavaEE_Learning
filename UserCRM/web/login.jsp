<%@page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
  </head>
  <body>
  	<div class="container" style="width: 400px;">
  		<h3 style="text-align: center;">管理员登录</h3>
        <form action="${pageContext.request.contextPath}/loginServlet" method="post" id="login_form">
	      <div class="form-group">
	        <label for="user">用户名：</label>
	        <input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名"/>
	      </div>
	      
	      <div class="form-group">
	        <label for="password">密码：</label>
	        <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
	      </div>
	      
	      <div class="form-inline">
	        <label for="vcode">验证码：</label>
	        <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码" style="width: 120px;"/>
	        <a href="javascript:refreshCode()"><img src="${pageContext.request.contextPath}/valiCodeServlet" title="看不清点击刷新" id="vcode"/></a>
	      </div>
	      <hr/>
	      <div class="form-group" style="text-align: center;">
	        <input class="btn btn btn-primary" type="submit" value="登录">
	       </div>
	  	</form>
		
		<!-- 出错显示的信息框 -->
	  	<div class="alert alert-warning alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" >
		  	<span>&times;</span></button>
		   <strong id="msg">${msg}</strong>
		</div>
  	</div>
  </body>

  <script>

      function refreshCode() {
          var img = document.getElementById("vcode");
          img.src = "${pageContext.request.contextPath}/valiCodeServlet?time="+new Date().getTime();
      }

      var msg = document.getElementById("msg");
      var ele_username = document.getElementById("user");
      var ele_password = document.getElementById("password");
      var ele_verifycode = document.getElementById("verifycode");
      function checkUserName(){

          return true;

          // var reg = /^[\w]{2,10}$/;
          // var flag =  reg.test(ele_username.value);
          // if(!flag){
          //     msg.innerHTML = "用户名至少2-10位！"
          // }else {
          //     msg.innerHTML ="";
          // }
          // return flag;

      }

      function checkPassword(){
          var reg = /^[\d]{3,10}$/;
         var flag =  reg.test(ele_password.value);
         if(!flag){
			msg.innerHTML = "密码至少3-10位数字形式！"
		 }else {
             msg.innerHTML ="";
		 }
          return flag;
      }

      function checkValiCode(){
          var reg = /^[\d]{3,5}$/;
          var flag =  reg.test(ele_verifycode.value);
          if(!flag){
              msg.innerHTML = "验证码至少3-5位！"
          }else {
              msg.innerHTML ="";
          }
          return flag;
      }


      // document.getElementById("user").onblur = checkUserName;
      document.getElementById("password").onblur = checkPassword;

      var login_form = document.getElementById("login_form");
      login_form.onsubmit = function (ev) {
          return checkUserName()&&checkPassword()&&checkValiCode;
      }
  </script>
</html>