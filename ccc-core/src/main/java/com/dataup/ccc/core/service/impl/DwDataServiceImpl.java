package com.dataup.ccc.core.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dataup.ccc.api.entity.ArgsEntity;
import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.service.DwDataService;
import com.dataup.ccc.api.util.HttpClientUtil;
import com.dataup.ccc.core.util.CustSerConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Title: DwDataServiceImpl.java
 * @Package com.dataup.ccc.core.service.impl
 * @Description: 数据仓库数据
 * @author xusheng.liu
 * @date 2015年9月22日 下午2:15:02
 * @version V1.0
 */
@Service("dwDataService")
public class DwDataServiceImpl implements DwDataService {
	private Logger logger = LogManager.getLogger(this.getClass());

	public ResultEntity query(String url, ArgsEntity argsEntity)
			throws Exception {
		logger.info("进入查询列表方法queryList:" + argsEntity);
		logger.entry(argsEntity);
		logger.entry(url);
		Map<String, String> entityMap = new HashMap<String, String>();
		packMap(argsEntity, entityMap);
		logger.info("参数对象::" + entityMap);
		String json = HttpClientUtil.post(url, entityMap, "utf-8");
		logger.info("远程接口:json==" + json);
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper objectMapper = new ObjectMapper();
		ResultEntity resultEntity = objectMapper.readValue(json,
				ResultEntity.class);
		logger.info("封装对象:resultEntity==" + resultEntity);
		return resultEntity;
	}

	/**
	 * @Description: 封装查询条件
	 * @author xusheng.liu
	 * @date 2015年10月19日 下午6:46:23
	 * @version V1.0
	 * @param argsEntity
	 * @param entityMap
	 */
	private void packMap(ArgsEntity argsEntity, Map<String, String> entityMap) {
		Integer funType = argsEntity.getFunType();
		if (argsEntity.getClassisId() != null
				&& !"".equals(argsEntity.getClassisId())) {// 分类(明细中)
			entityMap.put("classis", argsEntity.getClassisId());
		}
		if (StringUtils.isNotBlank(argsEntity.getClassis())) { // 分类
			entityMap.put("classis", argsEntity.getClassis());
		}
		if (StringUtils.isNotBlank(argsEntity.getZone())) { // 地区
			entityMap.put("zone", argsEntity.getZone());
		}
		if (StringUtils.isNotBlank(argsEntity.getSystemId())) { // 系统Id
			entityMap.put("system_type", argsEntity.getSystemId());
		}
		if (StringUtils.isNotBlank(argsEntity.getStatus())) { // 审核状态
			entityMap.put("status", argsEntity.getStatus());
		}
		if (StringUtils.isNotBlank(argsEntity.getAuditDate())) { // 审核状态
			entityMap.put("auditDate", argsEntity.getAuditDate()); // 审核时间
		}
		if (argsEntity.getPageNo() != null && argsEntity.getPageNo() > -1) { // 页码
			entityMap.put("start", argsEntity.getPageNo() + "");
		}
		if (argsEntity.getPageSize() != null && argsEntity.getPageSize() > 0) { // 页距
			entityMap.put("limit", argsEntity.getPageSize() + "");
		}
		if (CustSerConstant.QUERY_TYPE_LIST.equals(funType)) {// 区域分类列表
			entityMap.put("sortName", "start_time_");
		} else if (CustSerConstant.QUERY_TYPE_DETAIL.equals(funType)) {// 明细
			entityMap.put("sortName", "submit_time");
		} else if (CustSerConstant.QUERY_TYPE_PAY_LIST.equals(funType)) {// 费用支出
			entityMap.put("queryType", argsEntity.getPayCondition());
			entityMap.put("sortName", "auditdate");
		} else if (CustSerConstant.QUERY_TYPE_PAY_LIST.equals(funType)) {// 充值记录
			entityMap.put("sortName", "AUDIT_TIME");
		}
		entityMap.put("sortOrder", "desc"); // 排序规则
	}

	@Override
	public ResultEntity queryProjectInfo(String url, String systemId)
			throws IOException {
		logger.info("进入项目数量统计查询方法queryMap");
		logger.info("入参：" + systemId);
		String json = HttpClientUtil
				.get(url + "&system_type=" + systemId, null);
		logger.info("项目数量统计远程接口:json==" + json);
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper objectMapper = new ObjectMapper();
		ResultEntity resultEntity = objectMapper.readValue(json,
				ResultEntity.class);
		logger.info("项目数量统计封装对象:resultEntity==" + resultEntity);
		return resultEntity;
	}

	@Override
	public ResultEntity queryRecharge(String url, ArgsEntity argsEntity)
			throws IOException {
		logger.info("进入项目充值查询queryRecharge");
		logger.info("入参：" + argsEntity.getSystemId());
		Map<String, String> entityMap = new HashMap<String, String>();
		entityMap.put("system_type", argsEntity.getSystemId());
		entityMap.put("sortName", "AUDIT_TIME");
		entityMap.put("start", argsEntity.getPageNo() + "");
		entityMap.put("limit", argsEntity.getPageSize() + "");
		entityMap.put("sortOrder", "desc");
		String json = HttpClientUtil.post(url, entityMap, "utf-8");
		logger.info("充值查询远程接口:json==" + json);
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper objectMapper = new ObjectMapper();
		ResultEntity resultEntity = objectMapper.readValue(json,
				ResultEntity.class);
		logger.info("充值查询封装对象:resultEntity==" + resultEntity);
		return resultEntity;
	}

	@Override
	public ResultEntity loadProvincial(String url, String system_type)
			throws Exception {
		Map<String, String> entityMap = new HashMap<String, String>();
		entityMap.put("system_type", system_type);
		String json = HttpClientUtil.post(url, entityMap, "utf-8");
		logger.info("充值查询远程接口:json==" + json);
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper objectMapper = new ObjectMapper();
		ResultEntity resultEntity = objectMapper.readValue(json,
				ResultEntity.class);
		logger.info("充值查询封装对象:resultEntity==" + resultEntity);
		return resultEntity;
	}

	@Override
	public ResultEntity loadCity(String url, String system_type,
			String provincial) throws Exception {
		Map<String, String> entityMap = new HashMap<String, String>();
		entityMap.put("system_type", system_type);
		entityMap.put("provincial", provincial);
		String json = HttpClientUtil.post(url, entityMap, "utf-8");
		logger.info("充值查询远程接口:json==" + json);
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper objectMapper = new ObjectMapper();
		ResultEntity resultEntity = objectMapper.readValue(json,
				ResultEntity.class);
		logger.info("充值查询封装对象:resultEntity==" + resultEntity);
		return resultEntity;
	}

	@Override
	public ResultEntity doExp(String url, String submit_time_start,
			String submit_time_end, String systemId)
			throws Exception {
		logger.info("-->进入查询导出任务明细方法doExp");
		logger.info("-->入参:url=" + url + ";submit_time_start="
				+ submit_time_start + ";submit_time_end=" + submit_time_end
				+ ";systemId=" + systemId );
		Map<String, String> entityMap = new HashMap<String, String>();
		entityMap.put("systemId", systemId);
		entityMap.put("submit_time_start", submit_time_start);
		entityMap.put("submit_time_end", submit_time_end);
		String json = HttpClientUtil.post(url, entityMap, "utf-8");
		logger.info("---->查询导出成果远程接口:json==" + json);
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper objectMapper = new ObjectMapper();
		ResultEntity resultEntity = objectMapper.readValue(json,
				ResultEntity.class);
		logger.info("---->导出成果封装对象:resultEntity==" + resultEntity);
		return resultEntity;
	}

}
