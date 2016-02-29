<%@page import="com.dataup.ccc.web.util.MyCenterUtils"%>
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html>
<head>
<!-- <title>修改密码</title> -->
</head>
<body>
	<div class="container">
		<div class="list_main">
			<h2 class="list_breadcrumb">
				当前位置：&nbsp;&nbsp;<a href="">个人中心</a>&nbsp;&nbsp;<span class="arial">〉</span>&nbsp;&nbsp;<a
					href="">修改密码</a>
			</h2>
			<div class="login_list">
				<div class="rainbow_login rainbow_loginwidth emiletop">
					<div class="login_list emiletop">

						<div class=" msg-error" style="display: none;" id="msgerr">
							<b></b>两次输入的密码不一致，请重新输入
						</div>
						<p class="clearfix">
							<span class="sheet01"><label>旧密码</label><input id="oldpass"
								type="password" placeholder="当前登录密码" /></span>
						</p>
						<p class="clearfix">
							<span class="sheet01 "><label>新密码</label><input
								id="newpass" type="password" placeholder="六位以上包含字母和数字的组合" /></span><span class="textintro singleline"></span>
						</p>
						<p class="clearfix">
							<span class="sheet01 "><label>确认密码</label><input
								id="confirmpass" type="password" placeholder="再次输入密码" /></span><span class="textintro singleline"></span>
						</p>
						<p class="clearfix">
							<span class="sheet01 sheet02  "><label>验证码</label><input
								id="input_code" type="text" /></span><span class="checkcode" id="checkCode"></span><label><a
								href="##" class="re0" onclick="createCode()">看不清？</a></label>
						</p>
						<p></p>

						<p style="text-align:center">
							<a href="##" class="nextbtn" id="btnOK">确定</a>
						</p>

					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			createCode();
		})
		$("input").focus(function (){
			$("#msgerr").text("").hide();
		})
		$('#btnOK').click(function(){
			var oldpass=$('#oldpass').val();
			var newpass=$('#newpass').val();
			var confirmpass=$('#confirmpass').val();
			var input_code=$('#input_code').val();
			var pwd1="${customInfo.pwd1}";
			if(isNullOrEmpty(trim(oldpass))||isNullOrEmpty(trim(newpass))||isNullOrEmpty(trim(confirmpass))||(isNullOrEmpty(trim(input_code)))){
				$("#msgerr").text("含有空的信息，请输入").show();
				$("<b></b>").appendTo($("#msgerr"));
				return ;
			}
			if(newpass!=confirmpass){
				$("#msgerr").text("2次输入的密码不一致，请重新输入").show();
				$("<b></b>").appendTo($("#msgerr"));
				return ;
			}
			if(validate()){
				var jsonData = {"userName":"${customInfo.name}","oldPass":oldpass,"newPass":newpass};
				$.ajax({ 
			        type:"POST",
			        url:"<%=request.getContextPath() %>/mycenter/doModifyPass.html",
			        dataType:"json",
			        data:JSON.stringify(jsonData),
			        contentType : "application/json;charset=utf-8",
			        error:function(data) {
			        	alert("系统异常："+data.status);
			        },
			        success:function(data){ 
			        	if(data.success) {
			        		alert(data.desc);
			        		window.parent.location.href = "<%=request.getContextPath() %>/security/logout.html";
			        	}else  {
			        		$('#input_code').val('');
							createCode();
							$("#msgerr").text(data.desc).show();
							$("<b></b>").appendTo($("#msgerr"));
			        	}
			        }
				});
			}
	});
	</script>   	  
</body>

</html>
