package com.dataup.ccc.core.service.impl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataup.ccc.api.bean.FeedbackInfo;
import com.dataup.ccc.api.entity.FeedbackEntity;
import com.dataup.ccc.api.service.FeedbackService;
import com.dataup.ccc.core.dao.FeedbackDao;

/**
 * 
 * @ClassName: FeedbackServiceImpl
 * @Description: 客户对系统使用反馈
 * @author zhanqiao.huang
 * @date 2015年9月17日 下午4:00:57
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public void saveFeedbackInfo(FeedbackEntity entity) throws Exception {
		logger.info("保存客户反馈信息start-service");
		FeedbackInfo feedbackInfo=new FeedbackInfo();
		feedbackInfo.setCustomId(entity.getCustomId());
		feedbackInfo.setContext(entity.getContext());
		feedbackDao.saveFeedbackInfo(feedbackInfo);
		logger.info("保存客户反馈信息end-service");
	}

}
