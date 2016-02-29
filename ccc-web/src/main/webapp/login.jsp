<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- <title>登录</title> -->
<!-- 通用css -->
</head>
<body class="rainbow_bg01">
<link href="<%=request.getContextPath() %>/css/login.css" rel="stylesheet"/>
<div class="rainbow_login">
  <div class="logo"><img src="<%=request.getContextPath() %>/images/logo_login.png"  width="487" height="65"/></div>
  <form action="#">
   <div class="login_list">
    <p class="clearfix"><span class="sheet01 sheet_hover"><label>账&nbsp;&nbsp;号</label><input name="username" id="username" type="text" /></span></p>
    <p class="clearfix"><span class="sheet01 "><label>密&nbsp;&nbsp;码</label><input name="password" id="password" type="password" /></span></p>
    <p class="clearfix"><span class="sheet01 sheet02"><label>验证码</label><input name="input_code" id="input_code" type="text" maxlength="4" /></span>
    <span id="checkCode" class="checkcode"></span>
    <label><a href="##" class="re" onclick="createCode()">看不清？</a></label></p>
    <p class="clearfix"><a class="fogetpass" href="<%=request.getContextPath() %>/user/forgetpwd.html">忘记密码</a><input id="isRemember" type="checkbox"/>&nbsp;&nbsp;自动登录</p>
    <p class="btn"><a id="login"><span>登录</span></a></p>
        <p class="loadtext" style="text-align:center">您还没有账号？<a href="<%=request.getContextPath() %>/user/register.html">立即注册</a></p>
  </div>
  </form>
</div>
<!-- 通用js -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tools.js"></script>
<script type="text/javascript">
	$(function(){
		createCode();
	})
	function login(){
		var username=$('#username').val();
		var password=$('#password').val();
		if(isNullOrEmpty(trim(username))||(isNullOrEmpty(trim(password)))){
			alert("用户名或者密码不能为空！");
			return ;
		}
		var isRemember=$('#isRemember').is(':checked');
		if(validate()){
			var jsonData = {"username":username,"password":password,"isRemember":isRemember};
			$.ajax({ 
		        type:"POST",
		        url:"<%=request.getContextPath() %>/security/doLogin.html",
		        dataType:"json",
		        data:JSON.stringify(jsonData),
		        contentType : "application/json;charset=utf-8",
		        error:function(data) {
		        	alert("系统异常："+data.status);
		        },
		        success:function(data){ 
		        	if(data.success) {
		        		var b = window.top!=window.self;
		        		if(!b){
		        			window.location.href = "<%=request.getContextPath() %>/security/main.html";
		        		}else{
		        			window.parent.location.href = "<%=request.getContextPath() %>/security/main.html";
		        		}
		        	} else if(data.code=="E0JHSB"){//20151014增加激活失败处理
						alert("您的账户未激活，请登陆注册邮箱，查看激活邮件");
					} else {
						$('#input_code').val('');
						$('#password').val('');
						createCode();
						alert(data.errors);
		        	}
		        }
			});
		}
	}
	$('#login').click(function(){
		login();
	});
/* 	$('#input_code').keydown(function(e){
		if(e.keyCode==13){
		  login();
		}
	});
	$('#input_code').blur(function(){
		login();
	}) */
</script>
</body>
</html>