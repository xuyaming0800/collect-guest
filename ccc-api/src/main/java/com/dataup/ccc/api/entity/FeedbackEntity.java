package com.dataup.ccc.api.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName: FeedbackEntity
 * @Description: 信息反馈-实体
 * @author zhanqiao.huang
 * @date 2015年9月17日 下午3:40:26
 */
public class FeedbackEntity implements Serializable {

	private static final long serialVersionUID = 7444150599302263843L;

	private String context;
	private String customId;

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}


}
