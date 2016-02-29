package com.dataup.ccc.api.service;

import java.util.Map;

public interface MailService {

	/**
	 * 发送验证码
	 * @param mailAddress 邮件
	 * @param validtype 验证类型
	 * @return
	 */
	public Map<String, String> sendMailCode(String mailAddress, String validtype);
	
	
	/**
	 * 验证邮箱验证啊
	 * @param mail 邮箱
	 * @param validcode 验证码
	 * @param validtype 验证类型
	 */
	public void validateMailCode(String mail, String validcode, String validtype);
	
	
	/**
	 * 发送邮件修改密码
	 * @param username 用户名
	 * @param url url
	 * @param mail 邮箱地址
	 */
	public void sendModifyPwdMail(String username, String url, String mail);
	
	/**
	 * 同步文件次数预警邮件
	 * @param usernames
	 * @param content
	 */
	public void sendSynFileCountMessage(String usernames, String content);
	
	/**
	 * @Description: 发送邮箱激活码
	 * @author xudsheng.liu
	 * @date 2015年9月11日 下午4:13:08 
	 * @version V1.0 
	 * @param url
	 * @param validtype
	 * @param mail
	 * @return
	 */
	public void sendMailActivateCode(String username, String url, String mail,
			String validtype);
	
	/**
	 * @Description: 验证邮箱激活码
	 * @author xusheng.liu
	 * @date 2015年9月14日 下午2:08:22 
	 * @version V1.0 
	 * @param username
	 * @param activateCode
	 */
	public void activateEmail(String username,String activateCode);
	
	/**
	 * 
	 * @Methods Name sendMail 发送邮件
	 * @Create In 2015.09.29 zhanqiao.huang
	 * @param address
	 *            接受邮件邮箱地址，多个地址用分号(;)分割
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @param contentType
	 *            邮件格式 0：html格式； 1 文本格式
	 * @return boolean
	 * @throws Exception 
	 */
	public void sendEmail(String address, String subject, String content,
			int contentType);

	
}
