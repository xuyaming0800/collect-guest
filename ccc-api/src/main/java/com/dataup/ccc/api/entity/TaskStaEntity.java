package com.dataup.ccc.api.entity;

import java.io.Serializable;

/**
 * @Title: TestEntity.java
 * @Package com.dataup.ccc.web.base.security.entity
 * @Description: 任务统计对象
 * @author xusheng.liu
 * @date 2015年9月15日 下午5:22:09
 * @version V1.0
 */
public class TaskStaEntity implements Serializable{

	private static final long serialVersionUID = -4352350590683478714L;
	
	/**
	 * 区域
	 */
	private String areaOrClass;
	/**
	 * 采集开始时间 
	 */
	private String start_time;  
	/**
	 * 项目名称
	 */
	private String system_type; 
	/**
	 * 累计支付费用
	 */
	private Double payamt;
	/**
	 * 采集任务总数
	 */
	private Integer sall;
	/**
	 * 审核中数
	 */
	private Integer auditing;
	/**
	 * 审核合格数
	 */
	private Integer auditSuccess;
	/**
	 * 审核不合格数
	 */
	private Integer auditFall;
	public String getStart_time() {
		return start_time;
	}
	public Double getPayamt() {
		return payamt;
	}
	public Integer getSall() {
		return sall;
	}
	public Integer getAuditing() {
		return auditing;
	}
	public Integer getAuditSuccess() {
		return auditSuccess;
	}
	public Integer getAuditFall() {
		return auditFall;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public void setSystem_type(String system_type) {
		this.system_type = system_type;
	}
	public void setPayamt(Double payamt) {
		this.payamt = payamt;
	}
	public void setSall(Integer sall) {
		this.sall = sall;
	}
	public void setAuditing(Integer auditing) {
		this.auditing = auditing;
	}
	public void setAuditSuccess(Integer auditSuccess) {
		this.auditSuccess = auditSuccess;
	}
	public void setAuditFall(Integer auditFall) {
		this.auditFall = auditFall;
	}
	public String getAreaOrClass() {
		return areaOrClass;
	}
	public void setAreaOrClass(String areaOrClass) {
		this.areaOrClass = areaOrClass;
	}
	public String getSystem_type() {
		return system_type;
	}
}
