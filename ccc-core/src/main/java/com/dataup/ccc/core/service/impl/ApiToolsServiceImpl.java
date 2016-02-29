package com.dataup.ccc.core.service.impl;

import org.springframework.stereotype.Service;

import com.dataup.ccc.api.service.ApiToolsService;
import com.dataup.ccc.core.exception.CCCExceptionUtils;

 
@Service("apiToolsService")
public class ApiToolsServiceImpl implements ApiToolsService {
	
    /**
     * 获取错误信息
     */
	@Override
	public String getExceptionMessage(String code) {
		return CCCExceptionUtils.getInstance().getMessage(code);
	}
	
	 /**
     * 获取业务信息
     */
	@Override
	public String getMessage(String code) {
		return CCCExceptionUtils.getInstance().getMessage(code);
	}

}
