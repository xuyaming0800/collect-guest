<%@ page language="java" contentType="text/html; charset=utf8"	pageEncoding="utf8"%>
<%@ page language="java" import="java.util.*,com.dataup.ccc.web.constant.Config"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>注册</title>
<link href="${ctx }/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body class="rainbow_bg01">
	<div class="rainbow_login rainbow_loginwidth padtop">
		<div class="logo" style="text-align:left; margin-bottom:40px;">
			<img src="${ctx }/images/register_logo.png" />
		</div>
		<div class="login_list"
			style=" padding-left:160px; padding-right:160px;">
			<p class="clearfix">
				<img src="${ctx }/images/righticon.jpg" /> <span class="textbig">感谢您的注册！
				</span>
			</p>
			<!-- 输入框获取到焦点span调取sheet_hover -->
			<div class="clearfix">
				<span class="textintrozhuce"><span class="blue">验证邮箱已发送至您的邮箱：</span>${email_val }</span>
			</div>
			<div class="clearfix">
				<span class="textintrozhuce">请您在48小时内登陆您的邮箱，点击链接完成验证！</span>
			</div>

			<p class="clearfix">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
					class="textbig">没有收到邮件？</span>
			</p>
			<div class="clearfix">
				<span class="textintrozhuce">1、请检查您的邮箱地址是否正确，您可以返回<a href="${ctx }/user/register.html">重新填写</a></span>
			</div>

			<div class="clearfix">
				<span class="textintrozhuce">2、检查您的邮件垃圾邮箱</span>
			</div>
			<div class="clearfix">
				<span class="textintrozhuce">3、如果仍未收到，请尝试<a href="#" onclick="sendEmailAgain();return false;" id="resendEmail">重新发送</a></span>
			</div>
			<p></p>
			<p></p>
		</div>
	</div>
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		function sendEmailAgain(){
			$.ajax({
			   	type: "post",
			   	url: "${ctx }/user/registSuccess.html?email=${email_val}&username=${username_val}",
			   	success: function(msg){
			   		if(msg){
			   			alert("发送成功");
			   		}else{
			   			alert("发送失败");
			   		}
		   		}
			});
		}
	</script>
</body>
</html>
