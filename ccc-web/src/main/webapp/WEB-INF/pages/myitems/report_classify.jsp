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
          <h2 class="list_breadcrumb">当前位置：&nbsp;&nbsp;
          	<a href="#">我的项目</a>&nbsp;&nbsp;<span class="arial">〉</span>&nbsp;&nbsp;
          	<a href="#"><%=request.getParameter("projectName")%></a>&nbsp;&nbsp;<span class="arial">〉</span>&nbsp;&nbsp;分类统计</h2>
	<div class="floatright"><span class="lefttext" >统计类型：<input id="classis" value="" style="line-height: initial;"></span><a class="btnsizi" onclick="query();return false;">查找</a></div><h2 class="title14">分类统计表</h2>
    <div class="clear"></div> 
         <div class="list_table">
                <table>
                    <thead>
                        <tr>
                            <td>分类</td>
                            <td>采集开始时间</td>
                            <td>统计截至日期</td>
                            <td>累计支付费用</td>
                            <td>审核总数</td>
                            <td>审核中</td>
                            <td>审核合格</td>
                            <td>审核不合格</td>
                            <td>明细</td>
                        </tr>
                    </thead>
                    <tbody id="tbody_content">
                        <tfoot>
                        <tr>
                            <td>总计</td>
							<!-- <td></td>
							<td></td> -->
                            <td id="taskTotal" colspan="8"></td>
                            <!-- <td id="costTotal"></td>
                            <td id="auditingTotal"></td>
                            <td id="auditSuccessTotal"></td>
                            <td id="auditFallTotal"></td>
							<td></td> -->
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
					<div class="modal-dialog" style="width: 60%;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">分类任务明细<span style="color: red;">[共<font id="totalCount"></font>条]</span></h4>
							</div>
							<div class="modal-body">
								<div class="panel panel-primary">
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<td style="width: 5%">序号</td>
												<td style="width: 30%">采集任务名</td>
												<td style="width: 13%">区域</td>
												<td style="width: 17%">类型</td>
												<td style="width: 15%">采集时间</td>
												<td style="width: 10%">审核状态</td>
												<td style="width: 10%">费用</td>
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
system_type="<%=request.getParameter("system_type")%>",//项目id
content="<%=request.getContextPath()%>";//项目根路径
$(function() {
	//查询
	query();
});
var myDate = new Date();
myDate.setDate(myDate.getDate()-1);
function query(){
	$.post("<%=request.getContextPath()%>/myitems/report/queryClassifyList.html",{
		pageNo:pageNo,
		pageSize:pageSize,
		systemId:system_type,
		classis:$("#classis").val()
	},function(data) {
		if (data.success) {
			var containerBody = $("#tbody_content").empty();
			var htm = "";
			if(data.info.objectList != null && data.info.objectList.length > 0) {
				$.each(data.info.objectList,function(i,n){
					var url = "<%=request.getContextPath()%>/myitems/report/areaDetail.html";
					n.payamt = n.payamt==null?0:n.payamt;
				    htm +=  (i%2==1?'<tr class="odd">':'<tr>')
	    			+ "<td>" + n.classis + "</td>"
	    			+ "<td>" + n.start_time + "</td>"
 	    			+ "<td>" + myDate.Format("yyyy-MM-dd") + "</td>"
	    			+ "<td>" + n.payamt + "</td>"
	    			+ "<td>" + n.sall + "</td>"
	    			+ "<td>" + n.auditing + "</td>"
	    			+ "<td>" + n.auditSuccess + "</td>"
	    			+ "<td>" + n.auditFall + "</td>"
	    			+ "<td><a href='javascript:void(0);' onclick='setClassify(\""+n.classisId+"\",1);return false;'>查看</a></td>"
	    			+ "</tr>";
					$("#taskTotal").text("累计已支付" + (!n.total?0:n.total) + "元");
				});
				containerBody.html(htm);
				options.onPageClicked=function(event, originalEvent, type, page){
					pageNo = page;
					query();
		        };
		        options.currentPage = pageNo;
				initPage(options,"projectPage",data.info.totalCount,data.info.limit);
			}else {
				containerBody.html("<tr><td colspan='9'>未查询到数据</td></tr>");
			}
		} else {
			var containerBody = $("#tbody_content").empty();
			containerBody.html("<tr><td colspan='9'>"+data.desc+"</td></tr>");
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
function setClassify(op,no) {
	$.post("<%=request.getContextPath()%>/myitems/report/queryAreaStaDetailList.html",{
		pageNo:no,
		pageSize:pageSize,
		systemId:system_type,
		classisId:op
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
						.append("<td>"+(!n.area?'':n.area)+"</td>")
 						.append("<td>"+ (!n.type?'':n.type) +"</td>")
						.append("<td>"+new Date(n.submit_time).toLocaleDateString()+"</td>")
						.append("<td>"+getAuditStatus(n.task_status_key)+"</td>")
						.append("<td>"+(n.amount&&(n.task_status_key==3||n.task_status_key==4)?n.amount:'0.00')+"</td>").append("<tr/>").appendTo("#userDetail");
					});
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
						setClassify(op,pageNo_detail);
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
function getAuditStatus(op){
	var typeAudit;
	if(op==1){
		typeAudit = "审核中";
	}else if(op == 3){
		typeAudit = "审核通过";
	}else if(op == 4){
		typeAudit = "审核失败";
	}else if(op == 8){
		typeAudit = "申诉中";
	}
	return typeAudit;
}
</script> 
</body>
</html>
