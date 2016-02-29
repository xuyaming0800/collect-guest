<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
</head>
<body>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
	<div class="head ">
		<div class="header ">
			<div class="logo">
				<a href="#">&nbsp;</a>
			</div>
			<div class="nav_s">
				<span >尊敬的<span class="dropdown"  id="customList"><a href="#" class="name " id="customName">${customInfo.name }  <b class="caret"></b></a>
					<c:if test="${! empty customList }">
						<ul class="sub-menu dropdown-menu" style="min-width:80px;">
			 			 		<c:forEach var="item" items="${customList }">
			 			 			<li><a onclick="loadProjectMenu('${item.id}','${item.name}',this);return false;" class="name">${item.name}<b class="caret"></b></a></li>
			 			 		</c:forEach>
		                 </ul>
	                 </c:if>
                 </span>
				&nbsp;欢迎您！<span id="timer">
				</span>&nbsp;<a href="<%=request.getContextPath()%>/security/logout.html" class="name">安全退出</a> </span>
			</div>
			
			<nav >
				<div class="nav" id="ccc-nav">
					<ul>
						<li><a href="javascript:go2Page('<%=request.getContextPath()%>/index/firstPage.html')" class="home on">首页</a></li>
						<li class="dropdown all-camera-dropdown">
						<a href="#" class="dropdown-toggle ziliao" data-toggle="dropdown">我的项目 <b class="caret"></b></a>
						<ul class="sub-menu" id="project_main"></ul></li>
						<li class="dropdown all-camera-dropdown"><a href="#" class="xiaoxi">个人中心 <b class="caret"></b></a>
							<ul class="sub-menu">
								<li><a href="javascript:go2Page('<%=request.getContextPath()%>/mycenter/userInfo.html')">我的资料</a></li>
								<li><a href="javascript:go2Page('<%=request.getContextPath()%>/mycenter/verifyOldMail.html')">修改邮箱</a></li>
								<li><a href="javascript:go2Page('<%=request.getContextPath()%>/mycenter/goModifyPass.html')">修改密码</a></li>
							</ul>
						</li>
<!-- 						<li><a href="javascript:go2Page('<%=request.getContextPath()%>/downcenter/main.html')" class="download">下载中心</a></li> -->
						<li><a href="javascript:go2Page('<%=request.getContextPath()%>/feedback/main.html')" class="fankui">信息反馈</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
	
	

	<iframe id="subPage" width="100%" height="100%" frameborder="0" scrolling="no" src="<%=request.getContextPath()%>/index/firstPage.html"></iframe>
	<!--页尾 开始-->
	<div class="footer">
		<div>Copyright (C) 立德空间 2015-2020, All Rights Reserved</div>
	</div>
	<!--页尾 结束-->
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/ie10-viewport-bug-workaround.js"></script>
	<script src="<%=request.getContextPath()%>/js/offcanvas.js"></script>
	<script type="text/javascript" >
		function SetWinHeight(obj) {
			var win = obj;
			if (document.getElementById) {
				if (win && !window.opera) {
					if (win.contentDocument
							&& win.contentDocument.body.offsetHeight)
						win.height = win.contentDocument.body.offsetHeight;
					else if (win.Document && win.Document.body.scrollHeight)
						win.height = win.Document.body.scrollHeight;
				}
			}
		}
		$("#subPage").load(function (){
			SetWinHeight(this);
		})
		function go2Page(_url,sysId) {
			$('#subPage').attr('src', _url);
		}
		function loadProjectMenu(id,name,obj){
			$.post("<%=request.getContextPath()%>/base/queryProject.html",{
				customId: id,
				customName: name
			},function(data){
				if(data!=null&&data!=""){
					if(data.success){
						$("#project_main").empty();
						if(obj){
							$("#customName").text($(obj).text()).append($("<b></b>").attr("class","caret"));
						}
						var projs = data.info;
						var href1 = "<%=request.getContextPath()%>/myitems/baseinfo/itemInfo.html";//基本信息
						var href2 = "<%=request.getContextPath()%>/myitems/report/areaStatistics.html";//区域统计
						var href3 = "<%=request.getContextPath()%>/myitems/report/classifyStatistics.html";//分类统计
 						var href4 = "<%=request.getContextPath()%>/myitems/exports/result.html";//成果导出
						var href5 = "<%=request.getContextPath()%>/myitems/map/dataShow.html";//数据展示
						var href6 = "<%=request.getContextPath()%>/myitems/report/fareQuery.html";//费用查询
						var href7 = "<%=request.getContextPath()%>/myitems/recharge/recharge.html";//充值查询
						$.each(projs.objectList,function(i,n){
							//塞数据
							var a1 = $("<a></a>").attr("href","javascript:go2Page('"+href1+"?system_type="+n.id+"&projectName="+n.projectName+"',"+n.id+")").text("基本信息");
							var a2 = $("<a></a>").attr("href","javascript:go2Page('"+href2+"?system_type="+n.id+"&projectName="+n.projectName+"',"+n.id+")").text("区域统计");
							var a3 = $("<a></a>").attr("href","javascript:go2Page('"+href3+"?system_type="+n.id+"&projectName="+n.projectName+"',"+n.id+")").text("分类统计");
 							var a4 = $("<a></a>").attr("href","javascript:go2Page('"+href4+"?system_type="+n.id+"&projectName="+n.projectName+"',"+n.id+")").text("成果导出");
							var a5 = $("<a></a>").attr("href","javascript:go2Page('"+href5+"?system_type="+n.id+"&projectName="+n.projectName+"',"+n.id+")").text("数据展示");
							var a6 = $("<a></a>").attr("href","javascript:go2Page('"+href6+"?system_type="+n.id+"&projectName="+n.projectName+"',"+n.id+")").text("费用支出");
							var a7 = $("<a></a>").attr("href","javascript:go2Page('"+href7+"?system_type="+n.id+"&projectName="+n.projectName+"',"+n.id+")").text("充值查询");
							var content_ul = $("<ul></ul>").attr("class","sub-menu").append($("<li></li>").append(a1)).append($("<li></li>").append(a2))
								.append($("<li></li>").append(a3)).append($("<li></li>").append(a4)).append($("<li></li>")
								.append(a5)).append($("<li></li>").append(a6)).append($("<li></li>").append(a7));
							var content_li = $("<li></li>").attr("class","dropdown").append($("<a>"+n.projectName+"</a>")).append(content_ul);
							$("#project_main").append(content_li);
							hover();
						});
					}
				}
			},"json");
		}
		$(function() {
			//时间
			getTime();
			//查询用户的项目
			loadProjectMenu("${customInfo.id}","${customInfo.name}");
			hover();
			$('#subPage').css({
				'min-height' : $(window).outerHeight() - 221,
				'border' : 0
			});
		});
		function hover(){
			$('.dropdown').hover(function() {
				$(this).children('.sub-menu').slideDown(300);
			}, function() {
				$(this).children('.sub-menu').slideUp(300);
			});
		}
		function getTime(){
			var timer = "今天是"
				+getTimePart('y')+"年"
				+getTimePart('m')+"月"
				+getTimePart('d')+"号 星期"
				+getTimePart('w');
			$("#timer").text(timer);
		}
		function getTimePart(interval) {   
		    var myDate = new Date();
		    var partStr='';  
		    var Week = ['日','一','二','三','四','五','六'];  
		    switch (interval) {   
		        case 'y' :partStr = myDate.getFullYear();break;  
		        case 'm' :partStr = myDate.getMonth()+1;break;  
		        case 'd' :partStr = myDate.getDate();break;  
		        case 'w' :partStr = Week[myDate.getDay()];break;  
		        case 'ww' :partStr = myDate.WeekNumOfYear();break;  
		        case 'h' :partStr = myDate.getHours();break;  
		        case 'n' :partStr = myDate.getMinutes();break;  
		        case 's' :partStr = myDate.getSeconds();break;  
		    }  return partStr;  
		}
	</script>
</body>
</html>
