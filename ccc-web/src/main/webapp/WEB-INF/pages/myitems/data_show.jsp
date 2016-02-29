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
			<!-- <h2 class="list_breadcrumb" style="padding-top: 0;">当前位置：&nbsp;&nbsp;
				<a href="#">我的项目</a>&nbsp;&nbsp;<span class="arial">〉</span>&nbsp;&nbsp;
				<a href="#">经济环境</a>&nbsp;&nbsp;<span class="arial">〉</span>&nbsp;&nbsp;<a href="#">数据展示</a>
			</h2> -->
			<div class="container-fluid">
				<div class="row" style="height: 10px;margin-top:10px;">
					<div class="col-xs-12 col-md-12 padd">
						<div class="panel panel-default">
							<div id="" style="width: 100%;overflow: hidden;margin:0;font-family:'微软雅黑';">
								<table class="table" style="margin-bottom:0px;">
									<tr>
										<td><span>省：</span><select id="selectProvincial" class="form-control"
											style="display: inline;width: 50%">
<!-- 												<option selected="selected">北京市</option> -->
<!-- 												<option>上海市</option> -->
<!-- 												<option>天津市</option> -->
										</select></td>
										<td><span>市：</span><select id="selectCity" class="form-control"
											style="display: inline;width: 50%">
<!-- 												<option selected="selected">北京市</option> -->
<!-- 												<option>上海市</option> -->
<!-- 												<option>天津市</option> -->
										</select></td>
										<td><input type="radio" name="_audit" 
											value="" checked="checked"/>全部</td>
										<td><input type="radio" name="_audit" 
											value="1" />审核中</td>
										<td><input type="radio" name="_audit" 
											value="3" />审核成功</td>
										<td><input type="radio" name="_audit" 
											value="4" />审核失败</td>
										<td><input type="radio" name="_audit" 
											value="8" />申诉中</td>
										<td>
											<button type="button" class="btn btn-primary"
												onclick="javascript:addMapOverlay();">查询</button>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-md-12 padd">
						<div class="panel panel-default">
							<div class="panel-body">
								<div id="allmap"
									style="width: 100%;overflow: hidden;margin:0;font-family:'微软雅黑'; height: 700px"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=IMad2T4yqXPoGZ0zyufzlgO5"></script>
<script src="<%=request.getContextPath()%>/js/myitems/common.js"></script>
<script type="text/javascript">
var map,status="",
system_type="<%=request.getParameter("system_type")%>",//项目id
content="<%=request.getContextPath()%>";//项目根路径
function loadMap(city){
	function resizeBaiduMap() {
		var newHeight = $(window).innerHeight()
				- $("#allmap").offset().top - 65;
		$("#allmap").height(newHeight);
	}
	resizeBaiduMap();
	$(window).resize(resizeBaiduMap);
	map = new BMap.Map("allmap"); // 创建Map实例
	map.centerAndZoom(city, 12); // 设置中心点坐标和地图级别
	map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
	map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
	map.addControl(new BMap.ScaleControl({
		anchor : BMAP_ANCHOR_TOP_LEFT
	}));// 左上角，添加比例尺
	map.addControl(new BMap.NavigationControl()); //左上角，添加默认缩放平移控件
}
//加载所有的省
function loadProvincial(){
	var jsonData = {"system_type":system_type};
	$.ajax({ 
        type:"POST",
        url:"<%=request.getContextPath() %>/myitems/map/loadProvincial.html",
        dataType:"json",
        data:jsonData,
        error:function(data) {
        	alert("[loadProvincial]系统异常："+data.status);
        },
        success:function(data){ 
        	if(data.success) {
        		   if(data.info != null && data.info.length > 0) {
	        		   $.each(data.info,function(i,n){
							$("#selectProvincial").append('<option value="'+n.provincial+'">'+n.provincial+'</option>');
	        		    });
	        		   var provincial=$("#selectProvincial").val();//find("option:selected").text();
	        			if(!isNullOrEmpty(provincial))
	        				loadCity(provincial);
        		   }
        		   
        	}else  {
				alert(data.desc);
        	}
        }
	},"json");
}
//加载选中省下的城市
function loadCity(provincial){
	var jsonData = {"system_type":system_type,"provincial":provincial};
	$.ajax({ 
        type:"POST",
        url:"<%=request.getContextPath() %>/myitems/map/loadCity.html",
        dataType:"json",
        data:jsonData,
        error:function(data) {
        	alert("系统异常："+data.status);
        },
        success:function(data){ 
        	if(data.success) {
        		   if(data.info != null && data.info.length > 0) {
        			   $("#selectCity").empty();
	        		   $.each(data.info,function(i,n){
	        		    	 $("#selectCity").append('<option value="'+n.city+'">'+n.city+'</option>');
	        		    });
	        			var city=$("#selectCity").val();
	        			if(!isNullOrEmpty(city))
	        				loadMap(city);
        		   }
        	}else  {
				alert(data.desc);
        	}
        }
	},"json");
}
$(function() {
	//1、先加载省
	loadProvincial();
	//3、绑定省和市的级联
	$('#selectProvincial').change(function(){ 
		 provincial=$("#selectProvincial").find("option:selected").text();
		 loadCity(provincial);
	})
	//4、获取选中城市
	$('#selectCity').change(function(){ 
		 city=$("#selectCity").find("option:selected").text();
		 loadMap(city);
	})
})

function addMapOverlay() {
	map.clearOverlays();
    if (document.createElement('canvas').getContext) {  // 判断当前浏览器是否支持绘制海量点
  		var bigData=[];
  		status = $(":radio[name='_audit']:checked").val();
  		if(undefined == status||'undefined' == status) {
  			status = "";
  		}
    	$.post("<%=request.getContextPath()%>/myitems/map/map_task/query.html",
			{
				status : status,
				systemId : "<%=request.getParameter("system_type")%>",
				zone:$("#selectCity").find("option:selected").text()
			},
			function(data) {
				if (data != null && data.success && data.info.length > 0) {
					$.each(data.info, function(i, n) {
						var inData = [];
						inData.push(n.lon);
						inData.push(n.lat);
						bigData.push(inData);
					});
					var points = []; // 添加海量点数据
					for (var i = 0; i < bigData.length; i++) {
						points.push(new BMap.Point(
								bigData[i][0],
								bigData[i][1]));
					}
					var options = {
						size : BMAP_POINT_SIZE_SMALL,
						shape : BMAP_POINT_SHAPE_STAR,
						color : '#d340c3'
					}
					var pointCollection = new BMap.PointCollection(
							points, options); // 初始化PointCollection
					pointCollection.addEventListener('click',
							function(e) {
									alert('单击点的坐标为：' + e.point.lng+ ',' + e.point.lat); // 监听点击事件
							});
					map.addOverlay(pointCollection); //添加Overlay
				}else if( data.info.length == 0){
					alert("当前城市没有已采集任务");
				}else{
					alert(data.desc);
				}
			},"json");
	} else {
		alert('请在chrome、safari、IE8+以上浏览器查看本示例');
	}
}
</script>
</body>
</html>
