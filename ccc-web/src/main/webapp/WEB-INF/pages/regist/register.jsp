<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ page language="java" import="java.util.*,com.dataup.ccc.web.constant.Config"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注册</title>
<link href="${ctx}/css/jquery-ui.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/login.css" rel="stylesheet"/>
</head>
<body class="rainbow_bg01">
<div class="rainbow_login rainbow_loginwidth padtop">
	<div class="logo" style="text-align:left; margin-bottom:40px;">
		<img src="${ctx}/images/register_logo.png" />
	</div>
	<div class="login_list" style=" padding-left:160px; padding-right:160px;">
		<form action="#" id="regist_form">
			<div></div>
			<p class="clearfix">
				<span class="sheet01" id="username_span"><label>帐&nbsp;&nbsp;号</label><input
					name="username" type="text" id="username" class="required"  /></span><span
					class="textintro" id="info_username">6-20个字符，可以使用中文、字母、数字或下划线等！</span>
			</p><div></div>
			<p class="clearfix">
				<span class="sheet01" id="email_span"><label>邮&nbsp;&nbsp;箱</label><input
					name="email" type="text" id="email" class="required email" ></span> <span
					class="textintro" id="info_email">作为登录账号，请输入您正确的邮箱账号，
					以便顺利完成邮箱验证！ </span>
			</p><div></div>
			<p class="clearfix">
				<span class="sheet01" id="password_span"><label>密&nbsp;&nbsp;码</label><input
					name="password" type="password" id="password" class="required" /></span><span
					class="textintro" id="info_password">六位以上包含字母和数字的组合</span>
			</p><div></div>
			<p class="clearfix">
				<span class="sheet01" id="passwordNext_span"><label>确认密码</label><input
					name="passwordNext" type="password" id="passwordNext" class="required"/></span><span
					class="textintro">再次输入密码</span>
			</p>
			<p class="clearfix">
				<span class="sheet01" id="input_code_span"><label>验证码</label><input
					name="input_code" type="text" id="input_code" /></span><span
					id="checkCode" class="checkcode"></span> <label><a
					href="javascript:void(0);" class="re" onclick="createCode()">看不清？换一张</a></label>
			</p>

			<p class="btn" style="text-align:center;">
				<button type="submit" class="atonceload" id="regist">立即注册</button>
			</p>
			<p class="loadtext" style="text-align:center">
				我已经注册，现在就去<a href="${ctx}/login.jsp">登录</a>
			</p>
		</form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tools.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.method.min.js" ></script>
<script type="text/javascript">
$(function(){
	$("#username").focus();
	createCode();
	validateForm();
	$(".clearfix > span:first-child").click(function() {
		$(".clearfix > span:first-child").each(function(){
			$(this).attr("class","sheet01");
		});
		$(this).attr("class","sheet01 sheet_hover");
	})
})
function validateForm() {
	   var validator = $("#regist_form").validate({
			errorElement: "font",
			success: function(font) {
				font.parent("div").removeClass().empty();//验证通过的动态CSS
			},
			submitHandler: function(){
				doRegist();
			},
			errorPlacement: function(error, element) { 
				element.parent().parent().prev("div").show();
				error.appendTo( element.parent().parent().prev("div").addClass("msg-error msg_widths").empty().append(error)); 
				//error.appendTo($("#erorr_Info"));
				//$("#error_info_div").show();
			},
			rules: {
				username:{
					minlength: 4,
					maxlength: 20,
					remote:{
						url:"${ctx}/user/checkUserName.html",
						type:"post",
						dataType : "json"
					}
				},
				email:{
					remote:{
						url:"${ctx}/user/checkEmail.html",
						type:"post",
						dataType : "json"
					}
				},
				password:{
					minlength: 6
				},
				passwordNext:{
					equalTo:"#password"
				}
			},
			messages: {
				username: {remote:"该帐号已经存在",required:"请输入帐号",minlength:"请输入大于6位的帐号",maxlength:"请输入小于20位的帐号"},
				email: {remote:"该邮箱已经被使用",required:"请输入邮箱",email:"邮箱格式不正确",maxlength:"邮箱长度超过限制"},
				password: {required:"请输入密码",minlength:"请输入大于6位的密码"},
				passwordNext:{required:"请输入确认密码",equalTo:"两次密码不一致"}
			}
	});
}
function doRegist(){
   	var email = $("#email").val();
	var username = $.trim($("#username").val());
	var password = $("#password").val();
	if(isNullOrEmpty(email)){
		$("#email_span").attr("class","sheet01 sheet_wrong");
		$("#email").focus();
	}
	if(isNullOrEmpty(username)){
		$("#username_span").attr("class","sheet01 sheet_wrong");
		$("#username").focus();
	}
	if(isNullOrEmpty(password)){
		$("#password_span").attr("class","sheet01 sheet_wrong");
		$("#password").focus();
	}
	if(validate()){
		if(email!=""&&username!=""&&password!=""){
			$.ajax({
				type: "POST",
				url:"<%=Config.user_regist_url%>",
				dataType : 'jsonp',
				jsonp : 'jsoncallback',
				data : "email=" + email + "&username=" + username + "&password=" + password,
				success : function(data) {
					if(data.success){
						//跳转到注册成功页面
						alert("成功");
						window.location.href="${ctx}/user/registSuccess.html?email="+email+"&username="+username;
					}else{
						alert(data.msg);
					}
				}
			});
		} else {
			alert("必填信息为空");
		}
	} else {
		$("#input_code_span").attr("class","sheet01 sheet_wrong");
		$("#input_code").focus();
	}
}
$("#regist").bind("click",function(e){
	validateForm();//调用验证方法
})
</script>
</body>
</html>
