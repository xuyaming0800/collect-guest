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
				当前位置： <a href="#">我的项目</a><span class="arial">〉</span> <a href="#"><%=request.getParameter("projectName")%></a><span
					class="arial">〉</span>成果导出
			</h2>
			<div class="clear"></div>
			<form class="form-horizontal" role="form">
				<fieldset>
					<legend class="title14jiben">选择成果导出的时间范围</legend>
					<div class="form-group jibeninput">
						<label class="col-sm-2 control-label" for="input01">创建时间</label>
						<div class="col-sm-2" style="padding-right:0;">
							<div class="input-group date form_datetime"
								data-date=""
								data-date-format="yyyy-mm-dd hh:ii:ss"
								data-link-field="submit_time_start" id="startTimeDiv">
								<input class="form-control" size="16" type="text" value="" placeholder="提交时间 从"
									readonly> <span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-th"></span></span>
							</div>
							<input type="hidden" id="submit_time_start" name="beginTime" value="" />
						</div>
						<label class="col-sm-1 control-label" for="input01"
							style="width:30px;">到</label>
						<div class="col-sm-2" style="padding-right:0;">
							<div class="input-group date form_datetime"
								data-date=""
								data-date-format="yyyy-mm-dd hh:ii:ss"
								data-link-field="submit_time_end" id="endTimeDiv">
								<input class="form-control" size="16" type="text" value="" placeholder="提交时间 到"
									readonly> <span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-th"></span></span>
							</div>
							<input type="hidden" id="submit_time_end" name="endTime" value="" />
						</div>
					</div>
					<!-- <div class="form-group">
						<label class="col-sm-2 control-label" for="">选择导出文件</label>
						<div class="col-sm-3">
							<button type="button" class="btn btn-primary" onclick="$('.picpath').click()">浏览</button>
							<input type="file" name="picpath" id="picpath" style="display:none;" onChange="$('.path').value=this.value"> 
							<input name="path" readonly>
						</div>
					</div> -->
					<div class="form-group" >
						<p class="shenqingbtn" style="">
							<button type="button" class="btn btn-primary" onclick="exportFile();">导出</button>
						</p>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="clear"></div>
	</div>
<script src="<%=request.getContextPath()%>/js/myitems/common.js"></script>
<script type="text/javascript">
var system_type="<%=request.getParameter("system_type")%>";
content="<%=request.getContextPath()%>";//项目根路径,勿删
$(function() {
	//日历
	$('#startTimeDiv').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1,
        pickerPosition: "bottom-left"
    }).on('changeDate', function(ev){
    	setEndTime();
    });
	function setEndTime(){
		var star = $("#submit_time_start").val();
		$('#endTimeDiv').datetimepicker('remove').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	        showMeridian: 1,
	        pickerPosition: "bottom-left",
	        startDate :star
	    });
	}
});
function exportFile(){
	var star = $("#submit_time_start").val();
	var end = $("#submit_time_end").val();
	if(star && end ){
		if(system_type){
			downFile( star,end,system_type);
		}else{
			alert("缺少必要参数");
		}
	}else{
		alert("请选择创建起止时间");
	}
}

function downFile(beginTime,endTime,systemId){
	var form=$("<form>").attr({
		"style":"display:none",
		"target":"",
		"method":"post",
		"action":"<%=request.getContextPath()%>/myitems/exports/doExp.html"
	});
	var input1=$("<input>").attr({
		"type":"hidden",
		"name":"beginTime",
		"value":beginTime
	});

	var input2=$("<input>").attr({
		"type":"hidden",
		"name":"endTime",
		"value":endTime
	});

	var input3=$("<input>").attr({
		"type":"hidden",
		"name":"systemId",
		"value":system_type
	});
	$("body").append(form);//将表单放置在web中
	form.append(input1).append(input2).append(input3);
	form.submit();//表单提交 
}
</script>
</body>
</html>
