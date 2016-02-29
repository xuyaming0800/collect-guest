package com.dataup.ccc.web.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Title: Config.java
 * @Package com.dataup.ccc.web.constant
 * @Description: 获取配置文件信息
 * @author 刘旭升
 * @date 2015年9月8日 下午5:29:57
 * @version V1.0
 */
@Component
public class Config {
	/**
	 * 注册连接
	 */
	public static String user_regist_url;
	/**
	 * 验证用户名是否重复的url
	 */
	public static String check_username_is_exist_url;
	/**
	 * 验证邮箱是否重复的url
	 */
	public static String check_email_is_exist_url;
	/**
	 * 激活邮箱的url
	 */
	public static String update_email_sverify;
	public static String ccc_in_email_url_activate_email;
	
	/**
	 * 项目信息统计
	 */
	public static String dw_myiterm_project_info;
	/**
	 * 区域统计列表
	 */
	public static String dw_myiterm_statistics_area_list;
	/**
	 * 分类统计列表
	 */
	public static String dw_myiterm_statistics_classify_list;
	/**
	 * 统计明细展示
	 */
	public static String dw_myiterm_statistics_detail;
	/**
	 * 地图展示
	 */
	public static String dw_myiterm_map_show;
	/**
	 * 加载省或者城市
	 */
	public static String dw_myiterm_map_loadprovincial_city;
	/**
	 * 支付查询
	 */
	public static String dw_myiterm_pay_query;
	/**
	 * 充值查询
	 */
	public static String dw_myiterm_recharge;
	
	//项目信息
	public static String cc_myitems_iteminfo_show_url;
	//费用信息
	public static String cc_finance_myitems_feeinfo_show_url;
	
	//保存logo路径
	public static String base_logo_path;
	//成果导出信息来源接口
	public static String exportResult;
	
	@Value("${base_logo_path}")
	public void setBase_logo_path(String base_logo_path) {
		Config.base_logo_path = base_logo_path;
	}
	@Value("${exportResult}")
	public void setExportResult(String exportResult) {
		Config.exportResult = exportResult;
	}
	@Value("${dw_myiterm_map_show}")
	public void setDw_myiterm_map_show(String dw_myiterm_map_show) {
		Config.dw_myiterm_map_show = dw_myiterm_map_show;
	}
	
	@Value("${dw_myiterm_map_loadprovincial_city}")
	public void setDw_myiterm_map_loadprovincial_city(String dw_myiterm_map_loadprovincial_city) {
		Config.dw_myiterm_map_loadprovincial_city = dw_myiterm_map_loadprovincial_city;
	}
	@Value("${dw_myiterm_project_info}")
	public void setDw_myiterm_project_info(String dw_myiterm_project_info) {
		Config.dw_myiterm_project_info = dw_myiterm_project_info;
	}
	@Value("${dw_myiterm_statistics_area_list}")
	public void setDw_myiterm_statistics_area_list(
			String dw_myiterm_statistics_area_list) {
		Config.dw_myiterm_statistics_area_list = dw_myiterm_statistics_area_list;
	}
	@Value("${dw_myiterm_statistics_classify_list}")
	public void setDw_myiterm_statistics_classify_list(
			String dw_myiterm_statistics_classify_list) {
		Config.dw_myiterm_statistics_classify_list = dw_myiterm_statistics_classify_list;
	}
	@Value("${dw_myiterm_statistics_detail}")
	public void setDw_myiterm_statistics_detail(
			String dw_myiterm_statistics_detail) {
		Config.dw_myiterm_statistics_detail = dw_myiterm_statistics_detail;
	}
	@Value("${ccc_in_email_url_activate_email}")
	public void setCcc_in_email_url_activate_email(
			String ccc_in_email_url_activate_email) {
		Config.ccc_in_email_url_activate_email = ccc_in_email_url_activate_email;
	}
	@Value("${update_email_sverify}")
	public void setUpdate_email_sverify(String update_email_sverify) {
		Config.update_email_sverify = update_email_sverify;
	}
	@Value("${user_regist_url}")
	public void setUser_regist_url(String user_regist_url) {
		Config.user_regist_url = user_regist_url;
	}
	@Value("${check_username_is_exist_url}")
	public void setCheck_username_is_exist_url(
			String check_username_is_exist_url) {
		Config.check_username_is_exist_url = check_username_is_exist_url;
	}
	@Value("${check_email_is_exist_url}")
	public void setCheck_email_is_exist_url(String check_email_is_exist_url) {
		Config.check_email_is_exist_url = check_email_is_exist_url;
	}
	@Value("${dw_myiterm_pay_query}")
	public void setDw_myiterm_pay_query(String dw_myiterm_pay_query) {
		Config.dw_myiterm_pay_query = dw_myiterm_pay_query;
	}
	@Value("${dw_myiterm_recharge}")
	public void setDw_myiterm_recharge(String dw_myiterm_recharge) {
		Config.dw_myiterm_recharge = dw_myiterm_recharge;
	}
	/**
	 * 我的项目-基础信息
	 */
	@Value("${cc_myitems_iteminfo_show_url}")
	public void setCc_myitems_iteminfo_show_url(
			String cc_myitems_iteminfo_show_url) {
		Config.cc_myitems_iteminfo_show_url = cc_myitems_iteminfo_show_url;
	}
	@Value("${cc_finance_myitems_feeinfo_show_url}")
	public void setCc_finance_myitems_feeinfo_show_url(String cc_finance_myitems_feeinfo_show_url) {
		Config.cc_finance_myitems_feeinfo_show_url = cc_finance_myitems_feeinfo_show_url;
	}
	
}
