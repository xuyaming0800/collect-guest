package com.dataup.ccc.web.constant;

/**
 * @Title: CustomConstant.java
 * @Package com.dataup.ccc.web.constant
 * @Description: 客户中心常量
 * @author xusheng.liu
 * @date 2015年10月14日 下午6:51:25
 * @version V1.0
 */
public class CustomConstant {

	/**
	 * 客户邮箱激活成功状态
	 */
	public static String USER_EMAIL_ACTIVE_STATUS_OK = "Y";
	/**
	 * 客户邮箱激活失败code
	 */
	public static String USER_EMAIL_ACTIVE_STATUS_FALL_CODE = "E0JHSB";
	/**
	 * 客户邮箱未激活状态
	 */
	public static String USER_EMAIL_ACTIVE_STATUS_FALL = "N";
	
	/**
	 * 用来区分执行方法 1:分类统计和区域统计列表;	2:分类统计和区域统计明细;	3:支付统计列表
	 */
	public static Integer QUERY_TYPE_LIST = 1;
	public static Integer QUERY_TYPE_DETAIL = 2;
	public static Integer QUERY_TYPE_PAY_LIST = 3;
	
}
