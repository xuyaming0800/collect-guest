<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统反馈</title>
</head>
<body>
	<div class="container">
		<div class="list_main">
			<h2 class="list_breadcrumb">当前位置：&nbsp;&nbsp;信息反馈</h2>
			<div class="clear"></div>
			<form class="form-horizontal" role="form">
				<fieldset>
					<div class="form-group jibeninput">
						<div class="col-sm-12">
							<textarea class="form-control" rows="10" placeholder="请在此提交您宝贵的意见和建议..." id="context"></textarea>
						</div>
					</div>
					<div class="form-group" style="text-align:center">
						<p class="shenqingbtn" style="">
							<button id="btnFeedback" type="button">提交反馈</button>
						</p>
					</div>
				</fieldset>
			</form>
		</div>
		<!--list_main-->
		<div class="clear"></div>
	</div>
	<script type="text/javascript">
		$('#btnFeedback').click(function (){
			var context=$('#context').val();
			if(isNullOrEmpty(context)){
				alert("输入的内容不能为空！");
				return;
			}
			var jsonData = {"context":context,"customId":"<shiro:principal />"};
			console.log(jsonData);
			$.ajax({ 
		        type:"POST",
		        url:"<%=request.getContextPath() %>/feedback/saveFeedbackInfo.html",
		        dataType:"json",
		        data:JSON.stringify(jsonData),
		        contentType : "application/json;charset=utf-8",
		        error:function(data) {
		        	alert("系统异常："+data.status);
		        },
		        success:function(data){ 
		        	if(data.success) {
		        		alert(data.desc);
		        		$("#context").val("");
		        	}else  {
						alert(data.errors);
		        	}
		        }
			});
		})
	</script>
</body>
</html>
