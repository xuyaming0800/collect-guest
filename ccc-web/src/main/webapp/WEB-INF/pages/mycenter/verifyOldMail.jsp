<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html>
<head>
<title>修改邮箱-验证原邮箱</title>
</head>
<body>
	<div class="container">
		<div class="list_main">
			<h2 class="list_breadcrumb">
				当前位置：&nbsp;&nbsp;<a href="">个人中心</a>&nbsp;&nbsp;<span class="arial">〉</span>&nbsp;&nbsp;<a
					href="">修改邮箱</a>
			</h2>
			<div class="login_list">
				<div class="rainbow_login rainbow_loginwidth emiletop">
					<div class="login_list emiletop">
						<p class="clearfix">
							<img id="change_img"
								src="<%=request.getContextPath()%>/images/oldemile.jpg" />
						</p>
						<div class="msg-error" id="msgerr" style="display: none;">
							<b></b>
						</div>
						<p class="clearfix">
							<span class="sheet01"><label
								id="change_email_name">原邮箱</label><input id="iput_mail"
								type="text" /></span>
						</p>
						<p class="clearfix">
							<span class="sheet01 sheet02  "><label>验证码</label><input
								id="input_code" type="text" /></span><span class="checkcode"
								id="checkCode"></span><label><a href="#" 
								onclick="createCode();return false;">看不清？换一张</a></label>
						</p>
						<p></p>
						<p style="text-align:center">
							<a href="javascript:void(0);" class="nextbtn" id="completeBtn">完成</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="bg_msg_dialog_old" style="display: none;">
		<div class="alert-bg"></div>
		<div class="alertbox">
			<h3>提示信息</h3>
			<p class="alertbox-tit">
				<img src="<%=request.getContextPath()%>/images/righticon.jpg" />&nbsp;验证码已成功发到您的原邮箱。
			</p>
			<dl class="alertbox-dl">
				请输入验证码：
				<p class="clearfix">
					<span class="sheet01"><input id="code"
						type="text" /></span><label id="msgerr_code" class="msg-error" style="display: none;margin-left: 5px; width: auto;"></label>
				</p>
				<hr>
				<dd>注意：</dd>
				<dd>若没有收到验证邮件，请看看是否在邮件的垃圾箱里；</dd>
				<dd>
					稍等一会儿，若仍旧没有收到验证邮件，请<a class="here" href="javascript:void(0);"
						id="sendAgainSpan">点击这里</a>重新发送。<span id="seconds"></span><span
						id="sendAgain" style="display: none;">秒后可再次发送</span>
				</dd>
			</dl>
			<p class="submitbtn">
				<a href="javascript:void(0);" onclick="checkCode();return false;">确定</a>
			</p>
			<a href="javascript:void(0);"
				onclick="hide('bg_msg_dialog_old');return false;" class="closebtn"
				title="关闭">关闭</a>
		</div>
	</div>

	<div id="bg_msg_dialog_new" style="display: none;">
		<div class="alert-bg"></div>
		<div class="alertbox height2">
			<h3>提示信息</h3>
			<p class="alertbox-tit">
				<img src="<%=request.getContextPath()%>/images/righticon.jpg" />&nbsp;已成功设置新邮箱，请使用新邮箱！
			</p>
			<dl class="alertbox-dl">
				<dt></dt>
				<dd style="margin-bottom:10px; margin-top:30px; text-align:center;">
					<a href="#" class="mailbtn">确定</a>
				</dd>
			</dl>
			<a href="javascript:void(0);"
				onclick="hide('bg_msg_dialog_new');return false;" class="closebtn"
				title="关闭">关闭</a>
		</div>
	</div>
	<script type="text/javascript">
	var i,sd = "",sendFlag = false,level=1;
	function timer(){
		$("#seconds").text(i--);
		if(i<0){
			self.clearInterval(sd);
			$("#seconds").text("");
			$("#sendAgain").removeAttr("disabled")
			$("#sendAgain").hide();
			sendFlag = false;
		}
	}
	$(function(){
		createCode();
	})
	$('#completeBtn').click(function(){
		var inputMail=$('#iput_mail').val();
		var oldMail='${customInfo.mail}';
		var checkCode=$('#input_code').val();
		if(isNullOrEmpty(trim(inputMail))||(isNullOrEmpty(trim(checkCode)))){
			showErrorMsg("请输入邮箱地址或验证码");
			return ;
		}
		if(inputMail!=oldMail && level==1){
			showErrorMsg("邮箱输入错误，请重新输入");
			return ;
		}
		if(validate()){
			if(level==1 && !sendFlag){//旧邮箱
				$("#sendAgainSpan").bind("click",function(){
					if(!sendFlag)
						sendEmail();
				});
				sendEmail();
			} else if(level==2){//新邮箱
				//修改用户邮箱，调用远程接口，同时发邮件激活，设置为为激活。。验重
				if(inputMail==oldMail){
					showErrorMsg("新邮箱和原来的相同");return;
				}else if(!regxTes(inputMail)){
					showErrorMsg("请输入正确的邮箱");return;
				}
				$.post("<%=request.getContextPath()%>/mycenter/doModifyEmail.html",{
					email:inputMail,
					username:'${customInfo.name}'
				},function(data){
					if(data!=null){
						if(data.success){
							$(".emiletop").empty().append("完成修改邮箱，请您登陆你的邮箱去激活！");
						}
						alert(data.desc);
					}
				},"json");
			}
		}
	});
   function sendEmail(){
	   	i=59;
	   	$("#seconds").attr("color","red").text(60);
	   	$("#sendAgainSpan").attr("disabled","disabled");
		$("#sendAgain").show();
		sd = self.setInterval("timer()",1000);
		$.ajax({
		   	type: "post",
		   	dataType:"json",
		   	url: "<%=request.getContextPath()%>/mycenter/sendEmailByAjax.html?email=${customInfo.mail}&username=${customInfo.name}",
			success : function(data) {
				if (data != null && data.success) {
					sendFlag = data.success;
					show("bg_msg_dialog_old");
					createCode();
				} else {
					alert(data.desc);
				}
			}
		});
	}
   	//验证验证码
   	function checkCode(){
   		var code = $("#code").val();
   		if(isNullOrEmpty(trim(code))){
   			$("#msgerr_code").text("请输入验证码").show();
			$("<b></b>").appendTo($("#msgerr_code"));
			return ;
		}
   		$.ajax({
		   	type: "post",
		   	dataType:"json",
		   	url: "<%=request.getContextPath()%>/mycenter/checkCodeUpdateEmail.html?email=${customInfo.mail}&username=${customInfo.name}&code="+code,
			success : function(data) {
				if (data != null && data.success) { // 验证通过
					alert("验证通过");
					$("#iput_mail").val("");
					$("#input_code").val("");
					$("#change_img").attr("src","<%=request.getContextPath()%>/images/newemile.jpg");
					$("#change_email_name").text("新邮箱");
					level = 2;
					hide("bg_msg_dialog_old");
					$("#msgerr").hide();
					$("#msgerr_code").text("").hide();
				} else { // 验证失败
					$("#msgerr_code").text(data.desc).show();
					$("<b></b>").appendTo($("#msgerr_code"));
				}
			}
		});
	}
	//隐藏
	function hide(op) {
		$("#" + op).hide();
	}
	//显示
	function show(op) {
		$("#" + op).show();
	}
	//在msgerr中显示错误信息
	function showErrorMsg(op){
		$("#msgerr").text(op).show();
		$("<b></b>").appendTo($("#msgerr"));
	}
	//邮箱正则
	function regxTes(str){
		var re=new RegExp("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{1,8})+$","i");
		return re.test(str);
	}
	</script>
</body>
</html>
