package com.dataup.ccc.api.service;
 

public interface ApiToolsService {
	/**
	 * 根据错误码获取异常信息
	 * @param code
	 * @return
	 */
	public String getExceptionMessage(String code);
	
	/**
	 * 根据业务码获取业务信息
	 * @param code
	 * @return
	 */
	public String getMessage(String code);

}
