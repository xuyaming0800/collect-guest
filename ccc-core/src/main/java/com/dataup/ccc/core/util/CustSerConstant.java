package com.dataup.ccc.core.util;

/**
 * @Title: CustomConstant.java
 * @Package com.dataup.ccc.web.constant
 * @Description: 客户中心常量
 * @author xusheng.liu
 * @date 2015年10月14日 下午6:51:25
 * @version V1.0
 */
public class CustSerConstant {

	/**
	 * 用来区分执行方法 1:分类统计和区域统计列表;	2:分类统计和区域统计明细;	3:支付统计列表; 4:充值统计列表
	 */
	public static Integer QUERY_TYPE_LIST = 1;
	/**
	 * 用来区分执行方法 1:分类统计和区域统计列表;	2:分类统计和区域统计明细;	3:支付统计列表; 4:充值统计列表
	 */
	public static Integer QUERY_TYPE_DETAIL = 2;
	/**
	 * 用来区分执行方法 1:分类统计和区域统计列表;	2:分类统计和区域统计明细;	3:支付统计列表; 4:充值统计列表
	 */
	public static Integer QUERY_TYPE_PAY_LIST = 3;
	/**
	 * 用来区分执行方法 1:分类统计和区域统计列表;	2:分类统计和区域统计明细;	3:支付统计列表; 4:充值统计列表
	 */
	public static Integer QUERY_TYPE_RECHARGE_LIST = 4;
	
	/**
	 * 单条项目记录信息     key值为：PICP_加上项目ID字符串
	 */
	public final static String GET_ITEM_INFO_KEY = "PICP_";
	
	/**
	 * 按客户ID分类项目信息 key值为：CPICP_加上客户ID字符串
	 */
	public final static String GET_CUSTOM_PROJECTS_KEY = "CPICP_";
	
	public static final String USER_INFO_CACHE_PREFIX="BOSS_ONE_"; 
	
}
