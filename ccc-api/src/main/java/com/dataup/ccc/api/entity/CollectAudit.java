package com.dataup.ccc.api.entity;
public class CollectAudit implements java.io.Serializable {

	private static final long serialVersionUID = -5037448418274629916L;

	private String id;// 主键
	
	private Double amount;//金额
	private String task_name;//任务名称
	private String area;//区域
	private String submit_time;//提交时间
	private String type;//类型
	private String task_status_key;//审核状态
	private String statusString;// 状态
	public String getId() {
		return id;
	}
	public Double getAmount() {
		return amount;
	}
	public String getTask_name() {
		return task_name;
	}
	public String getArea() {
		return area;
	}
	public String getSubmit_time() {
		return submit_time;
	}
	public String getType() {
		return type;
	}
	public String getTask_status_key() {
		return task_status_key;
	}
	public String getStatusString() {
		return statusString;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setSubmit_time(String submit_time) {
		this.submit_time = submit_time;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTask_status_key(String task_status_key) {
		this.task_status_key = task_status_key;
	}
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
}
