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
				<a href="#"><%=request.getParameter("projectName")%></a><span class="arial">〉</span>充值记录
			</h2>
    		<div class="clear"></div> 
         	<div class="list_table">
                <table>
                    <thead>
                        <tr>
                            <td>序号</td>
                            <td>日期</td>
                            <td>充值金额</td>
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
	//查询
	query();
});
var myDate = new Date();
myDate.setDate(myDate.getDate()-1);
function query(){
	$.post("<%=request.getContextPath()%>/myitems/recharge/queryRecharge.html",{
		pageNo:pageNo,
		pageSize:pageSize,
		systemId:system_type
	},function(data) {
		if (data.success) {
			var taskTotal=0;
			var containerBody = $("#tbody_content").empty();
			var htm = "";
			if(data.info.objectList != null && data.info.objectList.length > 0) {
				var startNo=(pageNo-1)*pageSize+1;
				$.each(data.info.objectList,function(i,n){
					n.money = n.money==null?0:n.money;
				    htm +=  (i%2==1?'<tr class="odd">':'<tr>')
 	    			+ "<td>" + (startNo+i) + "</td>"
	    			+ "<td>" + new Date(n.auditTime).Format("yyyy-MM-dd")+ "</td>"
	    			+ "<td>" + n.money + "</td>"
	    			+ "</tr>";
				    taskTotal+=n.money*1;
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
</script> 
</body>
</html>
