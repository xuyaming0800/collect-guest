package com.dataup.ccc.core.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dataup.ccc.core.exception.BaseCoreException;

import autonavi.online.framework.property.PropertiesConfig;
import autonavi.online.framework.property.PropertiesConfigUtil;

/**
 * 
 * @ClassName: MailConst 
 * @Description: 邮件的属性信息
 * @author zhanqiao.huang
 * @date 2015年9月10日 下午6:06:49
 */
public class MailConst
{
	private  static final Logger logger = LogManager.getLogger(MailConst.class);
	private static PropertiesConfig pc = null;
	static {
		try {
			pc = PropertiesConfigUtil.getPropertiesConfigInstance();
		} catch (Exception e) {
			logger.error("读取配置文件出错");
			throw new BaseCoreException("读取配置文件出错");
		}
	}
	
	public static String mailServerHost = (String) pc.getProperty("system.mail.sendmail.host");
	public static String mailServerPort = (String) pc.getProperty("system.mail.sendmail.port");
	public static String mailUserName = (String) pc.getProperty("system.mail.sendmail.userName");
	public static String mailPassword = (String) pc.getProperty("system.mail.sendmail.passWord");
	public static boolean validate = Boolean.valueOf((String) pc.getProperty("system.mail.sendmail.auth"));
}
