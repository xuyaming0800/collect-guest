package com.dataup.ccc.api.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName: ItemInfoEntity 
 * @Description: 项目信息 
 * @author zhanqiao.huang
 * @date 2015年9月28日 下午1:34:06
 */
public class ItemInfoEntity implements Serializable {

	private static final long serialVersionUID = -3705938898599515357L;

	private String createTime;// 创建时间
	private String customName;// 创建人
	private String projectType;// 项目类型（主动、被动任务）
	private String status;// 项目创建状态
	private String projectDesc;// 项目描述

	private String advanceAmount;// 垫付
	private String thresholdAmount;// 阀值
	private String balanceAmount;// 余额（包括垫付金额）
	private String totalPay;// 累计支付金额
	
//	private String success;
//	private String code;
//	private String desc;
	public String getCreateTime() {
		return createTime;
	}

//	public String getSuccess() {
//		return success;
//	}
//
//	public void setSuccess(String success) {
//		this.success = success;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	public String getDesc() {
//		return desc;
//	}
//
//	public void setDesc(String desc) {
//		this.desc = desc;
//	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(String advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	public String getThresholdAmount() {
		return thresholdAmount;
	}

	public void setThresholdAmount(String thresholdAmount) {
		this.thresholdAmount = thresholdAmount;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(String totalPay) {
		this.totalPay = totalPay;
	}

}
