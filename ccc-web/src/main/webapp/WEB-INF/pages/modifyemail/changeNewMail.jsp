<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>下载中心</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="res/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="res/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="res/js/jquery-2.1.3.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="res/js/bootstrap.min.js"></script>
<!-- js -->
<script src="js/jquery-1.9.1.min.js"></script>
<script>
    $(document).ready(function() {
        $( '.dropdown' ).hover(
            function(){
                $(this).children('.sub-menu').slideDown(200);
            },
            function(){
                $(this).children('.sub-menu').slideUp(200);
            }
        );
    }); // end ready
</script>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="head ">
    	<div class="header ">
        	
       	  <div class="logo"><a href="#">&nbsp;</a></div>
            
            <div class="nav_s">
                <span><a href="#" class="name">Lilifen</a>&nbsp;欢迎您！今天是2015年08月20号   星期四 &nbsp;<a href="#" class="name">安全退出</a> </span>
               
            </div>
            <nav>
            <div class="nav ">
            
            	<ul>
                 <li><a href="#" class="home">首页</a></li>
                     <li class="dropdown"><a href="#" class="muban">测试项目模板  <b class="caret"></b></a></li>
                	<li class="dropdown all-camera-dropdown"><a href="#"  class="dropdown-toggle ziliao on"  data-toggle="dropdown"> 我的项目  <b class="caret"></b></a><ul class="sub-menu">
                <li class="dropdown"><a href="#">项目一</a>
                <ul class="sub-menu">
                                <li><a href="#">基本信息</a></li>
                                <li><a href="#">区域统计</a></li>
                                <li><a href="#">分类统计</a></li>
                                <li><a href="#">成果导出</a></li>
                                
                  </ul></li>
                <li><a href="#">项目二</a></li>
                <li><a href="#">项目三</a></li>
                </ul>
                </li>
                    <li><a href="#" class="xiaoxi">个人中心  <b class="caret"></b></a></li>
                    <li><a href="#" class="download">下载中心</a></li>
                    <li><a href="#" class="fankui">信息反馈</a></li>
                    
                </ul>
            </div>
            </nav>
        </div>
    </div>
    
    <div class="container">
      <div class="list_main">
          <h2 class="list_breadcrumb">当前位置：&nbsp;&nbsp;<a href="">个人中心</a>&nbsp;&nbsp;<span class="arial">〉</span>&nbsp;&nbsp;<a href="">修改邮箱</a></h2>
		 <div class="login_list" >        
	 		  <div class="rainbow_login rainbow_loginwidth emiletop">
   					<div class="login_list emiletop" >
  						
   					    <p class="clearfix"><img src="images/newemile.jpg" /></p>
   					    <div class=" msg-error"><b></b>账户名与密码不匹配，请重新输入</div>
 						<p class="clearfix"><span class="sheet01 sheet_wrong"><label>新邮箱</label><input name="" type="password" /></span></p> 
						 <p class="clearfix"><span class="sheet01 sheet02  "><label>验证码</label><input name="" type="text"  /></span><span class="checkcode">WWMM</span><label><a href="##" class="re0" >看不清？换一张</a></label></p>
 						 <p></p>
  	
  					   <p style="text-align:center"><a href="##" class="nextbtn"  >完成</a></p>
     
  					</div>
  					</div>
 			  </div>
          </div>
       </div>

           
   </div><!--container-->
   <!--页尾 开始-->
<div class="footer">
  <div>Copyright (C) 数聚联盟 20015-2020, All Rights Reserved</div>
</div>
<!--页尾 结束-->
   	  
</body>
</html>
