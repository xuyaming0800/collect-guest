package com.dataup.ccc.core.exception;

import com.dataup.ccc.api.exception.support.BaseSupportException;

import autonavi.online.framework.property.PropertiesConfigUtil;

public class BaseCoreException extends BaseSupportException {
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 */ 
	private static final long serialVersionUID = 9099843724255615287L;

	public BaseCoreException(String errorCode){
		super(getMessage(errorCode),errorCode);
	}
	public BaseCoreException(Throwable cause){
		super(cause);
	}
	public BaseCoreException(String errorCode,String status){
		super(getMessage(errorCode),errorCode,status);
	}
	
	private static String getMessage(String errorCode){
		String message="";
		try {
			message=(String)PropertiesConfigUtil.getPropertiesConfigInstance().getProperty(errorCode);
		} catch (Exception e) {
			message="获取信息失败,错误信息为"+e.getMessage();
		}
		if(message==null||message.equals("")){
			message=errorCode;
		}
		return message;
	}


}
