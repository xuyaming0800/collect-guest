/**
 * @Probject Name: MapABC_BOSS
 * @Path: com.mapabc.boss.mailMyAuthenticator.java
 * @Create By hongliang.gao
 * @Create In 2012-7-4 下午05:08:16
 */
package com.dataup.ccc.core.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * @ClassName: MyAuthenticator 
 * @Description: 验证
 * @author zhanqiao.huang
 * @date 2015年9月10日 下午5:53:47
 */
public class MyAuthenticator extends Authenticator {

	/**
	 * 
	 */
	public MyAuthenticator() {
	}

	String userName = null;
	String password = null;

	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
