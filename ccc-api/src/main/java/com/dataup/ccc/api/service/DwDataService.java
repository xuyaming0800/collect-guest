package com.dataup.ccc.api.service;

import java.io.IOException;
import com.dataup.ccc.api.entity.ArgsEntity;
import com.dataup.ccc.api.entity.ResultEntity;

/**
 * @Title: dwDataService.java
 * @Package com.dataup.ccc.api.service
 * @Description: 数据仓库数据
 * @author xusheng.liu
 * @date 2015年9月22日 下午2:13:21
 * @version V1.0
 */
public interface DwDataService {

	/**
	 * @Description: 统计/查询 明细/列表
	 * @author xusheng.liu
	 * @date 2015年10月19日 下午6:45:19 
	 * @version V1.0 
	 * @param url
	 * @param argsEntity
	 * @return
	 * @throws Exception
	 */
	public ResultEntity query(String url, ArgsEntity argsEntity)
			throws Exception;
	public ResultEntity loadProvincial(String url, String system_type)
			throws Exception;
	
	public ResultEntity loadCity(String url, String system_type,String provincial)
			throws Exception;
	/**
	 * @Description: 项目信息
	 * @author xusheng.liu
	 * @date 2015年9月23日 下午4:12:16
	 * @version V1.0
	 * @param dw_myiterm_project_info
	 * @param systemId
	 * @return
	 */
	public ResultEntity queryProjectInfo(String url, String systemId) throws IOException;

	/**
	 * @Description: 充值查询
	 * @author xusheng.liu
	 * @date 2015年10月22日 上午11:38:35 
	 * @version V1.0 
	 * @param dw_myiterm_recharge
	 * @param argsEntity
	 * @return
	 */
	public ResultEntity queryRecharge(String url,
			ArgsEntity argsEntity) throws IOException;
	
	/**
	 * @Description: 导出成果
	 * @author xusheng.liu
	 * @date 2015年11月25日 下午3:41:29 
	 * @version V1.0 
	 * @param exportResult
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param systemId 项目id
	 * @param out 
	 * @param path 保存路径
	 * @return
	 */
	public ResultEntity doExp(String exportResult, String beginTime,
			String endTime, String systemId) throws Exception;

}
