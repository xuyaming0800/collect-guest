<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/mail/reset.html";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- <title>忘记密码</title> -->
</head>

<body class="rainbow_bg01">
<link href="<%=path%>/css/login.css" rel="stylesheet" type="text/css" />
<div id="alert_div" style="display: none;">
	<div class="alert-bg"></div>
	<div class="alertbox">
	    <h3>提示信息</h3>
	    <p class="alertbox-tit"><img src="<%=path%>/images/righticon.jpg" />&nbsp;成功发送重置密码邮件。</p>
	    <dl class="alertbox-dl">
	        <dt>重置账号密码的邮件已经发送到您的密保邮箱，现在就去登录密保邮箱！</dt>
<!-- 	        <dd style="margin-bottom:10px; margin-top:10px;"> <a href="#" class="mailbtn">登录邮箱</a></dd> -->
	        <dd>注意：</dd>
	        <dd>若没有收到激活邮件，请看看是否在邮件的垃圾箱里；</dd>
	        <dd>稍等一会儿，若仍旧没有收到充值验证码邮件，请<a class="here" href="#">点击这里</a>重新发送。</dd>
	    </dl>
	    <p class="submitbtn"><a href="">知道了</a></p>
	    <a href="" class="closebtn" title="关闭">关闭</a>
	</div>
</div>

<div class="rainbow_login rainbow_loginwidth padtop" >
  <div class="logo" style="text-align:left; margin-bottom:40px;"><img src="<%=path%>/images/register_logoforget.png" /></div>
  <div class="login_list" >
  <p class="forgetpassword">找回密码</p>
    <!-- 输入框获取到焦点span调取sheet_hover -->
  <p class="clearfix"><img src="<%=path%>/images/zhanghao.jpg" /></p>
  <div class=" msg-error msg_widths" style="display: none;" id="msgerr"><b></b>两次密码不一致，请重新输入</div>
  <p class="clearfix"><span class="sheet01 "><label>账&nbsp;&nbsp;号</label><input id="input_account" type="text" /></span></p>
  <p class="clearfix"><span class="sheet01 sheet02  "><label>验证码</label><input id="input_code" type="text" maxlength="4" /></span>
  <span class="checkcode" id="checkCode"></span><label><a href="##" class="re0" onclick="createCode()">看不清？</a></label></p>
  <p></p>
   <p></p>
    <p class="btn" style="text-align:center;"><a id="next" class="atonceload" >下一步</a></p>
     <p></p>
      <p></p>
       <p></p>
        <p></p>
  </div>
</div>
<script type="text/javascript">
    $(function(){
    	$('#alert_div').hide();
    	createCode();
    })
     //1、绑定失去交点事件
	 $('#input_account').blur(function (){
		 var account=$('#input_account').val();
		 if(isNullOrEmpty(account)){
			 $("#msgerr").text("含有空的信息，请输入").show();
			 return;
		 }else{
			 $("#msgerr").text("").hide();
		 }
		 $.ajax({ 
		        type:"POST",
		        url:"<%=path%>/security/doAccountIsExists.html",
		        data:"account="+account,
		        error:function(data) {
		        	alert("系统异常："+data.status);
		        },
		        success:function(data){ 
		        	data = $.parseJSON(data);
		        	if(data.success) {
		    	 		//下一步
		        		$('#next').click(function (){
		        			//验证验证码
		        			if(validate()){
			        			var jsonData = {"username":account,"url":"<%=basePath%>","mail":data.info[0].mail};
				    			$.ajax({ 
				    		        type:"POST",
				    		        url:"<%=path%>/mail/sendModifyPwdMail.html",
				    		        dataType:"json",
				    		        data:JSON.stringify(jsonData),
				    		        contentType : "application/json;charset=utf-8",
				    		        error:function(data) {
				    		        	alert("系统异常："+data.status);
				    		        },
				    		        success:function(data){ 
				    		        	if(data.success) {
				    		        		$("#msgerr").text("").hide();
				    		        		$('#alert_div').show();
				    		        	}else  {
				    		        		$('#input_code').val('');
				    						createCode();
				    						$("#msgerr").text(data.errors).show();
				    		        	}
				    		        }
				    			});
			    			}
		        		})
		        	}else  {
		        		$("#msgerr").text(data.errors).show();
		        	}
		        }
		 });
	 })
</script>
</body>
</html>