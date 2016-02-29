<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ page language="java" import="java.util.*,com.dataup.ccc.web.constant.Config"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/jquery.validate.min.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<div class="list_main">
			<h2 class="list_breadcrumb">
				当前位置：&nbsp;&nbsp;<a href="">个人中心</a>&nbsp;&nbsp;<span class="arial">〉</span>&nbsp;&nbsp;<a
					href="">用户资料</a>
			</h2>
			<div class="clear"></div>
			<!--用户资料 start-->
			<form class="form-horizontal" role="form" action="#" method="post" 
				id="formUserInfo" enctype="multipart/form-data">
				<fieldset>
					<legend class="title14jiben">用户资料</legend>
					<div class="fleft col-sm-2">
						<div id="uploadpic_d" class="headimg">
							<img id="uploadpic_g" src="" onerror="javascript:this.src='<%=request.getContextPath()%>/images/headimg.jpg'" 
								style="width: 132px;height: 132px;"/>
							<input type="file" id="imgFile" name="imgFile" onchange="initImageObj(this,'uploadpic_g', 'uploadpic_d');"/>
							<span></span>
						</div>
					</div>
					<div class="fleft col-sm-10">
						<div class="form-group jibeninput ">
							<label class="col-sm-2 control-label" for="input01"><i>*</i>公司名称</label>
							<div class="col-sm-3">
								<input class="form-control required" value="" type="text" name="name" id="name"/>
								<span></span>
							</div>
							<label class="col-sm-2 control-label" for="ds_name">所在地</label>
							<div class="col-sm-3">
								<input class="form-control" value="" type="text" name="area" id="area"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label" for=""><i>*</i>联系人</label>
							<div class="col-sm-3">
								<input class="form-control required" value="" type="text" name="shortname" id="shortname"/>
								<span></span>
							</div>
							<label class="col-sm-2 control-label" for="ds_password"><i>*</i>手机</label>
							<div class="col-sm-3">
								<input class="form-control required" value="" type="text"
									name="mobile" id="mobile"/>
								<span></span>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label" for=""><i>*</i>座机电话</label>
							<div class="col-sm-3">
								<input class="form-control required" value="" type="text" name="telphone" id="telphone"/>
								<span></span>
							</div>
							<label class="col-sm-2 control-label" for="ds_password"><i>*</i>传真</label>
							<div class="col-sm-3">
								<input class="form-control required" value="" type="text"
									name="fax" id="fax"/>
								<span></span>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label" for=""><i>*</i>邮编</label>
							<div class="col-sm-3">
								<input class="form-control required" value="" type="text" name="postcode" id="postcode"/>
								<span></span>
							</div>
							<label class="col-sm-2 control-label" for="ds_password"><i>*</i>地址</label>
							<div class="col-sm-3">
								<input class="form-control required" value="" type="text" name="addr" id="addr"/>
								<span></span>
							</div>
						</div>
						<input type="hidden" name="userName" value="${customInfo.name}" >
						<input type="hidden" name="userId" value="${customInfo.id}" >
						<input type="hidden" name="id" id="id">
						<div class="form-group"
							style="text-align:center; margin-top:60px;">
							<p class="shenqingbtn" style="">
								<button id="save_btn" type="submit">保存</button>
							</p>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="clear"></div>
	</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.method.min.js" ></script>
<script type="text/javascript">
$(function(){
	var path = '<%=request.getContextPath()%>';
	//查询企业信息完成初始化
	init();
	
	//校验
	$.validator.addMethod( 'checkPost', function(value,element,params){
        var regex = /^[0-9a-zA-Z\u4E00-\u9FA5\(\)]*$/;
        return regex.test(value) || $.trim(value)== '';
     },'公司地址不能含有特殊符号' );
	$.validator.addMethod( 'checkTel', function(value,element,params){
      	var regex = /^(\d{3,4}-?)\d{7,8}$/;
	    return regex.test(value);
	 },'请输入正确格式的座机号码' );
	$.validator.addMethod( 'checkMobile', function(value,element,params){
       	var regex = /^1[3|5|7|8|][0-9]{9}$/;
	    return regex.test(value);
	 },'请输入正确格式的手机号码' );
	$.validator.addMethod( 'checkPost', function(value,element,params){
       	var regex = /^[1-9]{1}(\d+){5}$/;
	    return regex.test(value);
	 },'请输入正确格式的邮编' );
	$("#formUserInfo").validate({
		ignore: "",
		rules:{
			/* imgFile:{ required:true }, */
			name:{ required:true },
			shortname:{ required:true },
			mobile:{ required:true,'checkMobile':true },
			telphone:{ required:true,'checkTel':true },
			fax:{ required:true },
			addr:{ required:true },
			postcode:{ required:true,'checkPost':true }
		},
		messages: {
			/* imgFile:{required:"请上传LOGO图片"}, */
			name:{required:"请填写公司名称"},
			shortname:{required:"请填写联系人"},
			mobile:{required:"请填写手机号码"},
			telphone:{required:"请填写座机号码"},
			fax:{required:"请填写传真"},
			addr:{required:"请填写地址"},
			postcode:{required:"请填写邮编"},
		},
		submitHandler: function(form){
			$("#formUserInfo").ajaxSubmit({
				type:"post",
				url : '<%=request.getContextPath()%>/mycenter/saveEnterprise.html',
				dataType : 'json',
				success: function(data){
					if(data.success){
						alert(data.desc);
						init();
					}else{
						alert(data.desc);
					}
				}
	        });
		},
		errorElement: "font",
		errorPlacement: function(error, element) {
			error.appendTo( element.next("span").css({ "color": "#ff0011" }).empty().append(error));
		}
	});
});

function init(){
	$.ajax({
		type: "POST",
		url:"<%=request.getContextPath()%>/mycenter/queryEnterprise.html",
		dataType : 'json',
		success : function(data) {
			if(data.success){
				if(data.info!=null){
					$("#name").val(data.info.name);
					$("#area").val(data.info.area);
					$("#shortname").val(data.info.shortname);
					$("#fax").val(data.info.fax);
					$("#postcode").val(data.info.postcode);
					$("#addr").val(data.info.addr);
					$("#mobile").val(data.info.mobile);
					$("#telphone").val(data.info.telephone);
					$("#id").val(data.info.id);
					$("#imgFile").val("");
					$("#uploadpic_g").attr({ src: data.info.url, alt: "公司logo" });
				}
			}else{
				alert(data.desc);
			}
		}
	});
}

/*图片预览js*/
function initImageObj(fileObj, p_domg, p_domd){
    var allowExtention = ".jpg,.jpeg,.png";
    var domg =document.getElementById(p_domg);
    var domd = document.getElementById(p_domd);
    var extention = fileObj.value.substring(fileObj.value.lastIndexOf(".")+1).toLowerCase();            
    var browserVersion = window.navigator.userAgent.toUpperCase();
    if(allowExtention.indexOf(extention)>-1){
        if (browserVersion.indexOf("MSIE")>-1){
            domd.innerHTML = "";
            fileObj.select();
            domd.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            domd.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = fileObj.value;
        }else if(browserVersion.indexOf("FIREFOX")>-1){
            var firefoxVersion = parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);
            if(firefoxVersion<7){
                domg.src = fileObj.files[0].getAsDataURL();
            }else{
                domg.src = window.URL.createObjectURL(fileObj.files[0]);
            }
        }else if(fileObj.files){                 
            if(typeof FileReader !== "undefined"){
                var reader = new FileReader(); 
                reader.onload = function(e){
                    domg.src = e.target.result;
                }
                reader.readAsDataURL(fileObj.files[0]);
            }else if(browserVersion.indexOf("SAFARI")>-1){
                alert("图片预览暂时不支持safari浏览器!");
                return;
            }
        }else{
            alert("你的浏览器不支持图片预览!");
            return;
        }      
    }else{
        fileObj.value="";
        if(browserVersion.indexOf("MSIE")>-1){                        
            fileObj.select();
            document.selection.clear();
        }             
        fileObj.outerHTML=fileObj.outerHTML;
        alert("仅支持"+allowExtention+"为后缀名的文件!");
        return;
    }
}
</script>
</body>
</html>
