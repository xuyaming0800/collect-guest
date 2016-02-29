package com.dataup.ccc.core.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dataup.ccc.api.exception.BusinessRunException;

import autonavi.online.framework.property.PropertiesConfig;
import autonavi.online.framework.property.PropertiesConfigUtil;


public class CCCExceptionUtils {
	private static Logger logger = LogManager.getLogger(CCCExceptionUtils.class);
	private static CCCExceptionUtils cCExceptionUtils;
	private PropertiesConfig pc=null;
	
	private CCCExceptionUtils()throws Exception{
		pc=PropertiesConfigUtil.getPropertiesConfigInstance();
	}
	
	public static CCCExceptionUtils getInstance(){
		try {
			if(cCExceptionUtils==null){
				cCExceptionUtils=new CCCExceptionUtils();
			}
			return cCExceptionUtils;
		} catch (Exception e) {
			logger.error("初始化异常工具失败", e);
			throw new BusinessRunException("初始化异常工具失败，请检查");
		}
	}
	
	public String getMessage(String errorCode){
		String message="";
		message=(String)pc.getProperty(errorCode);
		return message;
	}

}
