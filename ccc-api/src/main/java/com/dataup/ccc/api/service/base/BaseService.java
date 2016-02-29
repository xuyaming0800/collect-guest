package com.dataup.ccc.api.service.base;

import com.dataup.ccc.api.entity.ResultEntity;

/**
 * @Title: BaseService.java
 * @Package com.dataup.ccc.api.service.base
 * @Description: 客户中心
 * @author xusheng.liu
 * @date 2015年9月21日 下午9:51:50
 * @version V1.0
 */
public interface BaseService {

	/**
	 * @Description: 查询项目
	 * @author xusheng.liu
	 * @date 2015年9月21日 下午9:53:54
	 * @version V1.0
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public ResultEntity queryProject(String url, String systemId)
			throws Exception;

}
