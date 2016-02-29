package com.dataup.ccc.api.service;

import com.dataup.ccc.api.entity.Enterprise;
 
/**
 * @Title: EnterpriseService.java
 * @Package com.dataup.ccc.api.service
 * @Description: 企业信息
 * @author xusheng.liu
 * @date 2015年9月18日 下午4:51:48
 * @version V1.0
 */
public interface EnterpriseService {

	/**
	 * @Description: 条件查询企业信息
	 * @author xusheng.liu
	 * @date 2015年9月18日 下午4:51:19 
	 * @version V1.0 
	 * @param string 
	 * @param argsEntity
	 * @return
	 * @throws Exception
	 */
	public Enterprise queryEnterpriseByCondition(Integer dsKey, Enterprise enterprise) throws Exception;

	public boolean saveEnterpriseByCondition(String name,Enterprise enterprise)throws Exception;

}
