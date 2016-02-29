/**
 * 发送邮件需要使用的基本信息 
 */
package com.dataup.ccc.core.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Message.RecipientType;

/**
 * 
 * @ClassName: MailSenderInfo
 * @Description: 发送邮件
 * @author zhanqiao.huang
 * @date 2015年9月10日 下午6:04:47
 */
public class MailSenderInfo {

	/**
	 * 
	 */
	public MailSenderInfo() {
		 this.setMailServerHost(MailConst.mailServerHost);
		 this.setMailServerPort(MailConst.mailServerPort);
		 this.setValidate(MailConst.validate);
		 this.setUserName(MailConst.mailUserName);
		 this.setPassword(MailConst.mailPassword);// 您的邮箱密码
		 this.setFromAddress(MailConst.mailUserName);
	}

	/**
	 * 发送邮件的服务器的IP和端口
	 */
	private String mailServerHost;
	private String mailServerPort = "25";
	/**
	 * mail中加入图片
	 */
	private List picturesPath;
	/**
	 * 邮件发送者的地址
	 */
	private String fromAddress;
	/**
	 * 邮件接收者的地址
	 */
	private String toAddress;

	private String[] toAddressArray;

	public String[] getToAddressArray() {
		return toAddressArray;
	}

	public void setToAddressArray(String[] toAddressArray) {
		this.toAddressArray = toAddressArray;
	}

	/**
	 * 登陆邮件发送服务器的用户名和密码
	 */
	private String userName;
	private String password;
	/**
	 * 是否需要身份验证
	 */
	private boolean validate = false;
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 邮件的文本内容
	 */
	private String content;
	/**
	 * 邮件附件的文件名
	 */
	private String[] attachFileNames;

	private RecipientType recipientType;

	/** */
	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		// Properties p = new Properties();
		// p.put("mail.smtp.host", this.mailServerHost);
		// p.put("mail.smtp.port", this.mailServerPort);
		// p.put("mail.smtp.auth", validate ? "true" : "false");
		// return p;
		// p.setProperty("mail.smtp.host", this.mailServerHost);
		// p.setProperty("mail.smtp.port", this.mailServerPort);
		// p.setProperty("mail.smtp.auth", validate ? "true" : "false");
		// p.setProperty("mail.smtp.socketFactory.port", this.mailServerPort);
		// p.setProperty("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// p.setProperty("mail.smtp.socketFactory.fallback", "false");
		// p.setProperty("mail.smtp.starttls.enable", "true");
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		// p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");//gmail需要
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}

	public List getPicturesPath() {
		return picturesPath;
	}

	public void setPicturesPath(List picturesPath) {
		this.picturesPath = picturesPath;
	}

	/**
	 * @Param RecipientType recipientType to set
	 */
	public void setRecipientType(RecipientType recipientType) {
		this.recipientType = recipientType;
	}

	/**
	 * @Return the RecipientType recipientType getRecipientType
	 */
	public RecipientType getRecipientType() {
		return recipientType;
	}

}
