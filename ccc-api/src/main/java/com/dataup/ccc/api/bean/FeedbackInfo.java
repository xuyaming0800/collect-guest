package com.dataup.ccc.api.bean;

import java.io.Serializable;
public class FeedbackInfo implements Serializable{

	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	private static final long serialVersionUID = 5244272662862358522L;
	private String id; //主键
	private String customId = ""; //客户ID   为了分库分表 
	private String context = "";
//	private Long createTime;// 创建时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 
//	public Long getCreateTime() {
//		if(createTime == null) {
//			createTime = new Date().getTime();
//		}
//		return createTime;
//	}
//	public void setCreateTime(Long createTime) {
//		this.createTime = createTime;
//	}
//	 
	
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	 
 
}
