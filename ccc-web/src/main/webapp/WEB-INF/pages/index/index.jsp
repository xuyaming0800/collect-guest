<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>主页面</title>
</head>
<body>
	<div class="container">
		<div class="list_main">
<!-- 			<div class="tips"> -->
<!-- 				已采集任务总数<b id="count_allNum"></b>条，合格<b id="count_successNum"></b>条，不合格<b id="count_fallNum"></b>条，审核中<b id="count_auditingNum"></b>条！ -->
<!-- 			</div> -->
			<img src="<%=request.getContextPath()%>/images/welbg.jpg" class="imgtop" />
			<div class="clear"></div>
			<div class="row-fluid" style="margin-top:20px; margin-bottom:20px; ">
				<div class="col-md-3" style="text-align:center;">
					<img src="<%=request.getContextPath()%>/images/dongtai.jpg" height="230" />

					<div class="hero-unit">
						<h1 style="margin-top:20px; margin-bottom:20px;">项目动态</h1>
						<p style="text-align:left;">
							实时显示项目状态、项目支出、项目余额等信息，提供决策依据，让您对项目动态了如指掌。</p>

					</div>
				</div>
			</div>

			<div class="col-md-3" style="text-align:center;">
				<img src="<%=request.getContextPath()%>/images/tongji.jpg" height="230" />

				<div class="hero-unit">
					<h1 style="margin-top:20px; margin-bottom:20px;">查询统计</h1>
					<p style="text-align:left">
						按区域、按类型分别统计，不同维度获取信息，细到每个任务，让您对项目细节明察秋毫。</p>
				</div>
			</div>

			<div class="col-md-3" style="text-align:center;">
				<img src="<%=request.getContextPath()%>/images/zhanshi.jpg" height="230" />
				<div class="hero-unit">
					<h1  style="margin-top:20px; margin-bottom:20px;">地图展示</h1>
					<p style="text-align:left">
						在地图上对每个任务进行标识，按审核状态分类展示，让您对项目完成情况一目了然。</p>

				</div>
			</div>

			<div class="col-md-3" style="text-align:center;">
				<img src="<%=request.getContextPath()%>/images/chaxun.jpg" height="230" />
				<div class="hero-unit">
					<h1  style="margin-top:20px; margin-bottom:20px;">支出明细</h1>
					<p style="text-align:left">
						每一次充值我们都有记录、每一笔花销我们都有详情，使您的项目费用变得清晰明了。</p>
				</div>

			</div>
			<div class="clear"></div> 
</body>
</html>