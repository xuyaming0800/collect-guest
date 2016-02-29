<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%
	String account=(String)request.getParameter("username");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>忘记密码</title>
</head>
<body class="rainbow_bg01">
<link href="<%=request.getContextPath() %>/css/login.css" rel="stylesheet" type="text/css" />
<div id="alert_div" style="display: none;">
	<div class="alert-bg"></div>
	<div class="alertbox height2">
	    <h3>提示信息</h3>
	    <p class="alertbox-tit"><img src="<%=request.getContextPath() %>/images/righticon.jpg" />&nbsp;已成功设置账户密码，请使用新密码登录！</p>
	    <dl class="alertbox-dl">
	        <dt></dt>
	        <dd style="margin-bottom:10px; margin-top:30px; text-align:center;"> 
	        	<a href="<%=request.getContextPath() %>/login.jsp" class="mailbtn">立即登录</a>
	        </dd>
	    </dl>
	    <a href="" class="closebtn" title="关闭">关闭</a>
	</div>
</div>
<!--alertbox end-->

<div class="rainbow_login rainbow_loginwidth padtop" >
  <div class="logo" style="text-align:left; margin-bottom:40px;"><img src="<%=request.getContextPath() %>/images/register_logoforget.png" /></div>
  <div class="login_list" >
  <p class="forgetpassword">找回密码</p>
    <!-- 输入框获取到焦点span调取sheet_hover -->
  <p class="clearfix"><img src="<%=request.getContextPath() %>/images/chongzhi.jpg" /></p>
 <p class="clearfix"><span class="sheet01"><label>新密码</label><input id="newpass" type="password" /></span>
 <span class="textintro textforget">6位以上的字母和数字的组合！</span></p>
 <p class="clearfix"><span class="sheet01"><label>确认密码</label><input id="confirmnewpass" type="password" /></span></p>
  
  <p></p>
   <p></p>
    <p class="btn" style="text-align:center;"><a id="next" class="atonceload" >下一步</a></p>
     <p></p>
      <p></p>
      
  </div>
</div>
<script type="text/javascript">
	var newpass,confirmnewpass,patrn;
    $(function(){
    	$('#alert_div').hide();
    })
	$('#next').click(function (){
		newpass=$("#newpass").val();
		confirmnewpass=$("#confirmnewpass").val();
		if(isNullOrEmpty(newpass)||isNullOrEmpty(confirmnewpass)){
			alert("新密码或者确认密码都不能为空！");
			return ;
		}
		if(newpass!=confirmnewpass){
			alert("输入密码不一置！");
			return ;
		}
// 		var patrn=/[^(?=\d{0,5}[a-zA-Z])(?=[a-zA-Z]{0,5}\d)[a-zA-Z0-9]{6}$]/;
		var jsonData = {"username":"<%=account%>","password":newpass};
		$.ajax({ 
	        type:"POST",
	        url:"<%=request.getContextPath() %>/security/doResetPass.html",
	        dataType:"json",
	        data:JSON.stringify(jsonData),
	        contentType : "application/json;charset=utf-8",
	        error:function(data) {
	        	alert("系统异常："+data.status);
	        },
	        success:function(data){ 
	        	if(data.success) {
	        		$('#alert_div').show();
	        	}else  {
	        		alert(data.desc);
	        	}
	        }
		});
	})
</script>
</body>
</html>