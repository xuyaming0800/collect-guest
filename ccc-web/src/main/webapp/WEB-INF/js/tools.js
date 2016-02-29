/** 去两边空格 **/
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
//判断是否为空
function isNullOrEmpty(strVal) {
	if (strVal == '' || strVal == null || strVal == undefined) {
		return true;
	} else {
		return false;
	}
}
//产生验证码
function createCode() {
	code = "";
	var codeLength = 4;// 验证码的长度
	var checkCode = $('#checkCode');
	var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');// 随机数
	for (var i = 0; i < codeLength; i++) {// 循环操作
		var index = Math.floor(Math.random() * 36);// 取得随机数的索引（0~35）
		code += random[index];// 根据索引取得随机数加到code上
	}
	checkCode.text(code);// 把code值赋给验证码
}
//校验验证码
function validate() {
	var inputCode = $('#input_code').val();// 取得输入的验证码并转化为大写
	inputCode=inputCode.toUpperCase();
	if (inputCode.length <= 0) { // 若输入的验证码长度为0
		alert("请输入验证码！"); // 则弹出请输入验证码
	} else if (inputCode != code) { // 若输入的验证码与产生的验证码不一致时
		alert("验证码输入错误！@_@"); // 则弹出验证码输入错误
		createCode();// 刷新验证码
		$('#input_code').val('');// 清空文本框
		return false;
	} else { // 输入正确时
		return true;
	}
}
//num表示要四舍五入的数,v表示要保留的小数位数。  
function decimal(num,v)  
{  
    var vv = Math.pow(10,v);  
    return Math.round(num*vv)/vv;  
} 