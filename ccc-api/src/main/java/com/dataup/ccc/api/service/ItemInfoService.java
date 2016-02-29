package com.dataup.ccc.api.service;

import com.dataup.ccc.api.entity.ResultEntity;

/**
 * 
 * @ClassName: ItemInfoService 
 * @Description: 项目信息
 * @author zhanqiao.huang
 * @date 2015年9月28日 上午11:59:01
 */
public interface ItemInfoService {

	
	/**
	 * 
	 * @Title: queryItemInfo 
	 * @Description: 项目基本信息
	 * @param @param url
	 * @param @param systemId
	 * @param @return
	 * @param @throws Exception 设定文件 
	 * @return ItemInfoEntity 返回类型 
	 * @throws
	 */
	public ResultEntity queryItemInfo(String url, String systemId)
			throws Exception;


	/**
	 * 
	 * @Title: queryItemFeeInfo 
	 * @Description: 项目费用信息
	 * @param @param url
	 * @param @param systemId
	 * @param @return
	 * @param @throws Exception 设定文件 
	 * @return ItemInfoEntity 返回类型 
	 * @throws
	 */
	public ResultEntity queryItemFeeInfo(String url, String systemId)
			throws Exception;

}
