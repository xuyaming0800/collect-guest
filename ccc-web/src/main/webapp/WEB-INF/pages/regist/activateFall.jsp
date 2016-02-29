<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ page language="java"
	import="java.util.*,com.dataup.ccc.web.constant.Config"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>注册</title>
<link href="${ctx }/css/login.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.timer_span{
	color: graytext;
}
.timer_font{
	color: red;
	font-weight: bolder;
	margin-left: 5px;
}
</style>
</head>
<body class="rainbow_bg01">
	<div class="rainbow_login rainbow_loginwidth padtop">
		<div class="logo" style="text-align:left; margin-bottom:40px;">
			<img src="${ctx }/images/register_logo.png" />
		</div>
		<div class="login_list"
			style=" padding-left:160px; padding-right:160px;">
			<p class="clearfix">
				<img src="${ctx }/images/wrongicon.png" height="50px" /> <span
					class="textbig">邮箱激活失败！ （或当前激活链接已经失效）</span>
			</p>
			<!-- 输入框获取到焦点span调取sheet_hover -->
			<div class="clearfix">
				<span class="textintrozhuce">尊敬的用户，您的邮箱激活失败！<a href="${ctx}/user/register.html">重新注册</a>！
				</span><button id="sendEmail_btn" class="mailbtn" style="border: none;" >重新发送激活邮件</button>
				<span class="timer_span" hidden="hidden"><font class="timer_font">60</font>&nbsp;秒后可再次发送</span>
			</div>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
		</div>
	</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	var i=60,sd = "";
	$(function(){
		$("#sendEmail_btn").click(function(){
			$.ajax({
				type : "post",
				url : "${ctx }/user/registSuccess.html",
				data : {
					email:"<%=request.getParameter("email")%>",
					username:"<%=request.getParameter("username")%>"
				},
				success : function(msg) {
					if (msg) {
						$("#sendEmail_btn").text("发送成功").attr("disabled","disabled").css({
							"color": "graytext","background-color": "buttonface"
						});
						$(".timer_span").show();
						i=59;
						sd = self.setInterval("timer()",1000);
					} else {
						alert("发送失败");
					}
				}
			});
		});
	});
	
	function timer(){
		$(".timer_font").text(i--);
		if(i<0){
			self.clearInterval(sd);
			$(".timer_span").hide();
			$("#sendEmail_btn").text("重新发送激活邮件").removeAttr("disabled").css({
				"color": "#fff","background-color": "#fd9315"
			});
		}
	}
</script>
</body>
</html>
