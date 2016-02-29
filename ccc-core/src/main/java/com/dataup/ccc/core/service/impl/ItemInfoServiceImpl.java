package com.dataup.ccc.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autonavi.online.framework.util.json.JsonBinder;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.dataup.ccc.api.bean.Pagination;
import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.service.ItemInfoService;
import com.dataup.ccc.api.util.HttpClientUtil;
import com.dataup.ccc.core.util.CustSerConstant;
import com.dataup.ccc.core.util.RedisUtilComponent;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @ClassName: ItemInfoServiceImpl
 * @Description: 项目信息
 * @author zhanqiao.huang
 * @date 2015年9月28日 下午1:40:27
 */
@Service("itemInfoService")
public class ItemInfoServiceImpl implements ItemInfoService {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private RedisUtilComponent redisUtilComponent;

	@Override
	public ResultEntity queryItemInfo(String url, String systemId)
			throws Exception {
		logger.info("进入项目基础信息方法queryItemInfo");
		logger.info("入参systemId：" + systemId);
		logger.info("入参url：" + url);
		String key = null;
		String json = null;
		List<Object> objectList = new ArrayList<Object>();
		ResultEntity resultEntity = new ResultEntity();
		key = CustSerConstant.GET_ITEM_INFO_KEY+ systemId;
		JsonBinder jb = JsonBinder.buildNormalBinder(false);
		Pagination pagination = new Pagination();
		Object obj = redisUtilComponent.getRedisJsonCache(key, Object.class, jb);
		if (obj == null) {
			json = HttpClientUtil.get(url + "&ownerId=" + systemId, null);
			logger.info("项目基础信息对象远程接口:json==" + json);
			if (StringUtils.isBlank(json))
				return null;
			ObjectMapper objectMapper = new ObjectMapper();
			resultEntity = objectMapper.readValue(json, ResultEntity.class);
			logger.info("项目基础信息对象for接口调用:resultEntity==" + resultEntity);
		} else {
			Map m = (Map)obj;
			String userId=(String)m.get("projectLeaderId");
			key=CustSerConstant.USER_INFO_CACHE_PREFIX+userId;
			Map objUser= (Map)redisUtilComponent.getRedisJsonCache(key, Object.class, jb);
			logger.info("获取用户缓存信息：" + objUser);
			m.put("projectLeaderName", objUser.get("name").toString());
			objectList.add(m);
			pagination.setObjectList(objectList);
			resultEntity.setInfo(pagination);
			logger.info("项目基础信息对象for缓存获取:resultEntity==" + resultEntity);
		}
		
		return resultEntity;

	}

	@Override
	public ResultEntity queryItemFeeInfo(String url, String systemId)
			throws Exception {
		logger.info("进入项目费用信息方法queryItemInfo");
		logger.info("入参systemId：" + systemId);
		logger.info("入参url：" + url);
		String json = HttpClientUtil.get(url + "&ownerId=" + systemId, null);
		logger.info("项目费用信息远程接口:json==" + json);
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper objectMapper = new ObjectMapper();
		ResultEntity resultEntity = objectMapper.readValue(json,
				ResultEntity.class);
		logger.info("项目费用信息对象封装对象:resultEntity==" + resultEntity);
		return resultEntity;
	}

}
