package com.dataup.ccc.api.service;

import com.dataup.ccc.api.entity.FeedbackEntity;

/**
 * 
 * @ClassName: FeedbackService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhanqiao.huang
 * @date 2015年9月17日 下午3:38:35
 */
public interface FeedbackService {

	/**
	 * 
	 * @Title: save 
	 * @Description: 保存提交的信息
	 * @param @param entity
	 * @param @throws Exception 设定文件 
	 * @return void 返回类型 
	 * @throws
	 */
	public void saveFeedbackInfo(FeedbackEntity entity) throws Exception;

}
