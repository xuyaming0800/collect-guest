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
</head>
<body class="rainbow_bg01">
	<div class="rainbow_login rainbow_loginwidth padtop">
		<div class="logo" style="text-align:left; margin-bottom:40px;">
			<img src="${ctx }/images/register_logo.png" />
		</div>
		<div class="login_list"
			style=" padding-left:160px; padding-right:160px;">
			<p class="clearfix">
				<img src="${ctx }/images/righticon.jpg" /> <span class="textbig">恭喜您，已成功激活邮箱！
				</span>
			</p>
			<div class="clearfix">
				<span class="textintrozhuce">尊敬的用户，您的邮箱已成功激活！请<a href="${ctx}/login.jsp">点击登录</a>吧！
				</span><a href="${ctx}/login.jsp" class="mailbtn">立即登录</a>
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
	<script type="text/javascript"
		src="${ctx}/js/jquery-1.11.3.min.js"></script>
	<%-- <script type="text/javascript">
		function sendEmailAgain() {
			$.ajax({
				type : "post",
				url : "${ctx }/user/registSuccess.html?email=<%=request.getParameter("email")%>&username=<%=request.getParameter("username")%>",
				success : function(msg) {
					if (msg) {
						alert("发送成功");
					} else {
						alert("发送失败");
					}
				}
			});
		}
	</script> --%>
</body>
</html>
