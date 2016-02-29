package com.dataup.ccc.core.service.impl.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autonavi.online.framework.util.json.JsonBinder;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.dataup.ccc.api.bean.Pagination;
import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.service.base.BaseService;
import com.dataup.ccc.api.util.HttpClientUtil;
import com.dataup.ccc.core.util.CustSerConstant;
import com.dataup.ccc.core.util.RedisUtilComponent;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @ClassName: FeedbackServiceImpl
 * @Description: 客户对系统使用反馈
 * @author zhanqiao.huang
 * @date 2015年9月17日 下午4:00:57
 */
@Service("baseService")
public class baseServiceImpl implements BaseService {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private RedisUtilComponent redisUtilComponent;

	@Override
	public ResultEntity queryProject(String url, String customId)
			throws Exception {
		logger.info("进入项目方法queryProject");
		logger.info("入参customId：" + customId);
		logger.info("入参url：" + url);
		
		String key = null;
		String json = null;
		ResultEntity resultEntity = new ResultEntity();
		key = CustSerConstant.GET_CUSTOM_PROJECTS_KEY+ customId;
		JsonBinder jb = JsonBinder.buildNormalBinder(false);
		Pagination pagination = new Pagination();
		List<Object> objectList = redisUtilComponent.getRedisJsonCache(key, List.class, jb);
		if (objectList == null) {
			json = HttpClientUtil.get(url + "&customId=" + customId, null);
			logger.info("用户项目远程接口:json==" + json);
			if (StringUtils.isBlank(json))
				return null;
			ObjectMapper objectMapper = new ObjectMapper();
			resultEntity = objectMapper.readValue(json, ResultEntity.class);
			logger.info("用户项目对象:resultEntity==" + resultEntity);
		} else {
			logger.info("从缓存中获取信息:objectList==" + objectList);
			pagination.setObjectList(objectList);
			resultEntity.setSuccess(true);
			resultEntity.setInfo(pagination);
		}
		return resultEntity;
	}

}
