<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
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
			<h2 class="list_breadcrumb">当前位置：
				<a href="#">我的项目</a><span class="arial">〉</span>
				<a href="#"><%=request.getParameter("projectName")%></a><span class="arial">〉</span>费用支出
			</h2>
			<div class="floatright">
				<span class="lefttext" >时间范围：
					<label style="margin-right: 10px;"><input type="radio" name="pay_condition" checked="checked" value="1">天</label>
					<label style="margin-right: 10px;"><input type="radio" name="pay_condition" value="2">月</label>
				</span>
			</div>
    		<div class="clear"></div> 
         	<div class="list_table">
                <table>
                    <thead>
                        <tr>
                            <td>日期</td>
                            <td>采集任务数</td>
                            <td>扣款</td>
                        </tr>
                    </thead>
                    <tbody id="tbody_content">
                        <tfoot>
                        <tr>
                            <td>总计</td>
							<td></td>
                            <td id="taskTotal"></td>
                        </tr>
                        </tfoot>
                    </tbody>
                </table>
				<div style="text-align: right">
					<ul id="projectPage" class="pagination-sm">
					</ul>
				</div>
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">
									<span style="font-weight: bold;color: darkblue;">支出明细</span>
									<span id="pay_detail_time" style="color: blue; background-color: gainsboro;" class="modal_span"></span> 
									<span style="color: red;background-color: gainsboro;">[共<font id="totalCount"></font>条]</span>
								</h4>
							</div>
							<div class="modal-body">
								<div class="panel panel-primary">
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<td>序号</td>
												<td>采集任务名</td>
												<td>类型</td>
												<td>扣款日期</td>
												<td>扣除费用</td>
											</tr>
										</thead>
										<tbody id="userDetail">
										</tbody>
									</table>
								</div>
								<div>
									<ul id="projectPage_detail" class="pagination pagination-sm" style="margin: 0;">
									</ul>
								</div>
							</div>
<!-- 							<div class="modal-footer"> -->
<!-- 								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> -->
<!-- 							</div> -->
						</div>
					</div>
				</div>
            </div>
      </div>
      <div class="clear"></div>   
   </div>
<script src="<%=request.getContextPath()%>/js/myitems/common.js"></script>   
<script type="text/javascript">
var pageNo = 1,//页码
pageNo_detail = 1,//明细页码
pageSize=10,//分页大小
old = null,//用来保存原来的对象
system_type="<%=request.getParameter("system_type")%>",//项目id
content="<%=request.getContextPath()%>";//项目根路径
$(function() {
	$("input[name='pay_condition']").each(function(){//循环绑定事件  
		if(this.checked){
			old = this; //如果当前对象选中，保存该对象  
		}  
		this.onclick = function(){  
			if(this != old){ 
				query(this);
				old = this; 
			} 
		}  
	});  
	//查询
	query();
});
var myDate = new Date();
myDate.setDate(myDate.getDate()-1);
function query(op){
	var dayOrMonth;
	if(op){
		var checkVal = $(op).val();
		if(checkVal==1)
			dayOrMonth = "day";
		else dayOrMonth = "month";
	}else
		dayOrMonth = "day";
	$.post("<%=request.getContextPath()%>/myitems/report/queryPayList.html",{
		pageNo:pageNo,
		pageSize:pageSize,
		systemId:system_type,
		payCondition:dayOrMonth
	},function(data) {
		if (data.success) {
			var taskTotal=0;
			var containerBody = $("#tbody_content").empty();
			var htm = "";
			if(data.info.objectList != null && data.info.objectList.length > 0) {
				$.each(data.info.objectList,function(i,n){
					var url = "<%=request.getContextPath()%>/myitems/report/areaDetail.html";
					n.payamt = n.payamt==null?0:n.payamt;
				    htm +=  (i%2==1?'<tr class="odd">':'<tr>')
 	    			+ "<td>" + n.auditDate + "</td>"
	    			+ "<td>" + n.taskNum+ "</td>"
	    			+ "<td><a href='javascript:void(0);' onclick='getDetail(1,\"" + n.auditDate + "\");return false;'>" + n.payamt + "</a></td>"
	    			+ "</tr>";
				    taskTotal+=n.payamt*1;
				});
				containerBody.html(htm);
				$("#taskTotal").text(decimal(taskTotal,2));
				options.onPageClicked=function(event, originalEvent, type, page){
					pageNo = page;
					query();
		        };
		        options.currentPage = pageNo;
				initPage(options,"projectPage",data.info.totalCount,data.info.limit);
			}else {
				containerBody.html("<tr><td colspan='3'>未查询到数据</td></tr>");
			}
		} else {
			var containerBody = $("#tbody_content").empty();
			containerBody.html("<tr><td colspan='3'>"+data.desc+"</td></tr>");
		}
	},"json");
}
//分页参数
var options = {
   	size:"sm",
    bootstrapMajorVersion:3,
    currentPage:1,
    numberOfPages:5,
    onPageClicked:function(event, originalEvent, type, page){
	}
}
function initPage(options,id,totalCount,limit) {
	//分页显示
	var pageElement = $("#"+id+"");
     options.totalPages=Math.floor(totalCount%limit==0?(totalCount/limit):(totalCount/limit+1));
    pageElement.bootstrapPaginator(options);
}
function getDetail(no,time) {
	$.post("<%=request.getContextPath()%>/myitems/report/queryAreaStaDetailList.html",{
		pageNo:no,
		pageSize:pageSize,
		systemId:system_type,
		auditDate:time,
		funType:2
	},function(data) {
		$("#userDetail").empty();
		if(data!=null&&data!=""){
			if(data.success){
				var len=data.info.objectList.length;
				if(data.info.objectList != null &&  len> 0) {
					var startNo=(no-1)*pageSize+1;
					$.each(data.info.objectList,function(i,n){
						var tr = "<tr/>";
						if(i%2==0){
							tr = "<tr class='odd'></tr>";
						}
						$(tr).append("<td>"+(startNo+i)+"</td>")
						.append("<td>"+(!n.task_name?'':n.task_name)+"</td>")
 						.append("<td>"+ (!n.type?'':n.type) +"</td>")
						.append("<td>"+time+"</td>")
						.append("<td>"+(n.payamt&&(n.task_status_key==3||n.task_status_key==4)?n.payamt:'0.00')+"</td>").append("<tr/>").appendTo("#userDetail");
					});
					$("#pay_detail_time").text(time);
					$('#myModal').modal({
					     modal: true,             // 创建模式对话框
					     autoOpen: false,
					     buttons: {
					         "Ok": function() {
					              $(this).dialog('close');
					         },
					         "Cancel": function() {
					             $(this).dialog('close');
					         }
					     }
					});
					$("#totalCount").addClass("color","red").text(data.info.totalCount);
					options.onPageClicked=function(event, originalEvent, type, page){
						pageNo_detail = page;
						getDetail(pageNo_detail,time);
			        };
			        options.currentPage = no;
					initPage(options,"projectPage_detail",data.info.totalCount,data.info.limit);
				}else{
					var containerBody = $("#userDetail").empty();
					containerBody.html("<tr><td>"+data.desc+"</td></tr>");
					alert("未查询到数据");
				}
			} else {
				alert("系统错误");
			}
		}else{
			alert("参数key值有误");
		}
	},'json');
};
</script> 
</body>
</html>
