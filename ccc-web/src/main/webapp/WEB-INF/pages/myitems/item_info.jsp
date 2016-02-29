<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="container">
		<div class="list_main">
			<div class="tips">
				已采集任务总数<b id="count_allNum"></b>条，合格<b id="count_successNum"></b>条，不合格<b id="count_fallNum"></b>条，审核中<b id="count_auditingNum"></b>条！
			</div>
			<h2 class="list_breadcrumb">
				当前位置：&nbsp;&nbsp;<a href="#">我的项目</a>&nbsp;&nbsp;<span
					class="arial">〉</span>&nbsp;&nbsp;<a href="#"><%=request.getParameter("projectName")%></a>&nbsp;&nbsp;<span
					class="arial">〉</span>&nbsp;&nbsp;基本信息
			</h2>
			<div class="clear"></div>
			<form class="form-horizontal" role="form">
				<input type="hidden" id="hidCxt">
				<input type="hidden" id="hidProject">
				<fieldset>
					<legend class="title14jiben">项目基本信息</legend>
					<div class="form-group jibeninput">
						<label class="col-sm-2 control-label" for="input01">创建时间</label>
						<div class="col-sm-3">
							<div class="form-control" id="createTime"></div>
						</div>
						<label class="col-sm-2 control-label" for="ds_name">创建人</label>
						<div class="col-sm-3">
							<div class="form-control" id="projectLeaderName"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="">项目状态</label>
						<div class="col-sm-3">
							<div class="form-control" id="status"></div>
						</div>
						<label class="col-sm-2 control-label" for="ds_password">项目类型</label>
						<div class="col-sm-3" >
							<div class="form-control" id="projectType"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="">项目概述</label>
						<div class="col-sm-8">
							<div class="form-control" id="projectDesc" style="height: 100px"></div>
						</div>
					</div>
					<div class="form-group" style="text-align:center">
<!-- 						<p class="shenqingbtn" style=""> -->
							<button class="btn btn-success" id="btnStart" type="button">申请启动</button>
							&nbsp;&nbsp;&nbsp;
							<button class="btn btn-danger" id="btnStop" type="button">申请暂停</button>
<!-- 						</p> -->
					</div>
				</fieldset>
			</form>
			<form class="form-horizontal" role="form">
				<fieldset>
					<legend class="title14jiben">费用信息</legend>
					<div class="form-group jibeninput">
						<label class="col-sm-2 control-label" for="input01">余额</label>
						<div class="col-sm-3">
							<div class="form-control" id="balanceAmount">0.00</div>
						</div>
						<label class="col-sm-2 control-label" for="ds_name">累计已支付(实时)</label>
						<div class="col-sm-3">
							<div class="form-control" id="totalPay">0.00</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="">余额阈值</label>
						<div class="col-sm-3">
							<div class="form-control" id="thresholdAmount">0.00</div>
						</div>
						<label class="col-sm-2 control-label" for="ds_password">垫付值</label>
						<div class="col-sm-3">
							<div class="form-control" id="advanceAmount">0.00</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<script src="<%=request.getContextPath()%>/js/myitems/common.js"></script>
	<script type="text/javascript">
	var system_type="<%=request.getParameter("system_type")%>",//项目id
	content="<%=request.getContextPath()%>";//项目根路径
	function sendMail(btn){
		var recipients=$("#projectLeaderName").text();
		if(isNullOrEmpty(recipients)){
			alert("接受人不能不为空！");
			return ;
		}
		var jsonData = {"userName":recipients,"button":btn,"hidCxt":$("#hidCxt").val(),"hidProject":$("#hidProject").val()};
		$.ajax({ 
	        type:"POST",
	        url:"<%=request.getContextPath() %>/myitems/baseinfo/sendMail.html",
	        dataType:"json",
	        data:jsonData,
	        error:function(data) {
	        	alert("系统异常："+data.status);
	        },
	        success:function(data){ 
	        	if(data.success) {
	        		 alert("邮件已经发送！请查收。");
	        	}else  {
					alert(data.desc);
	        	}
	        }
		},"json");
	}
	$("#btnStart").click(function(){
		sendMail("btnStart");
	})
	$("#btnStop").click(function(){
		sendMail("btnStop");
	})
	$(function(){
		var jsonData = {"ownerId":"<%=request.getParameter("system_type")%>"};
		$.ajax({ 
	        type:"POST",
	        url:"<%=request.getContextPath() %>/myitems/baseinfo/queryItemInfo.html",
	        dataType:"json",
	        data:jsonData,
	        error:function(data) {
	        	alert("系统异常："+data.status);
	        },
	        success:function(data){ 
	        	if(data.success) {
	        		var n=data.info.itemInfo.info.objectList;
	        		var m=data.info.itemFee.info;
	        		if(n != null && n.length>0) {
	        			var createTime=(new Date(n[0].createTime)).Format("yyyy-MM-dd hh:mm:ss");
	        			var status=n[0].status;
	        			if(status==1){
	        				$("#status").text("正常");
	        				$("#btnStart").attr("disabled",true);
	        			}else{
	        				$("#status").text("暂停");
	        				$("#btnStop").attr("disabled",true);
	        			}
	        			$("#createTime").text(createTime);
	        			$("#projectLeaderName").text(n[0].projectLeaderName);
	        			$("#projectDesc").text(n[0].projectDesc);
	        			var hidCxt="【客户名】：${customInfo.name}"+"    【项目名称】："+n[0].projectName;
	        			$("#hidCxt").val(hidCxt);
	        			$("#hidProject").val(n[0].projectName);
	        		}else{
	        			
	        		}
	        		if(m != null) {
	        			
	        			$("#balanceAmount").text(decimal(parseFloat(m[0].balanceAmount)-parseFloat(m[0].advanceAmount),2));//余额值
	        			$("#totalPay").text(m[0].totalPay);
	        			$("#thresholdAmount").text(m[0].thresholdAmount);
	        			$("#advanceAmount").text(m[0].advanceAmount);//垫付值
	        		}else{
	        			$("#balanceAmount").text("0.00");
	        			$("#totalPay").text("0.00");
	        			$("#thresholdAmount").text("0.00");
	        			$("#advanceAmount").text("0.00");
	        		}
	        	}else  {
					alert(data.desc);
	        	}
	        }
		},"json");
	})
	</script>
</body>
</html>
