package com.dataup.ccc.web.base.security.entity;

/**
 * 
 * @ClassName: MailEntity
 * @Description: 邮件信息
 * @author zhanqiao.huang
 * @date 2015年9月11日 上午10:52:26
 */
public class MailEntity {

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getValidcode() {
		return validcode;
	}

	public void setValidcode(String validcode) {
		this.validcode = validcode;
	}

	public String getValidtype() {
		return validtype;
	}

	public void setValidtype(String validtype) {
		this.validtype = validtype;
	}

	private String username;
	private String url;
	private String mail;
	private String validcode;
	private String validtype;

}
