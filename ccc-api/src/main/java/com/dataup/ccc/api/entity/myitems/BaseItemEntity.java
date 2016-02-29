package com.dataup.ccc.api.entity.myitems;

import java.io.Serializable;

public class BaseItemEntity implements Serializable {

	private static final long serialVersionUID = 4474744963250636946L;
	private int taskAllNum;
	private int taskQualifiedNum;
	private int taskUnQualifiedNum;
	private int taskAuditingNum;
	private int systemType;
	private int id=1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskAllNum() {
		return taskAllNum;
	}

	public void setTaskAllNum(int taskAllNum) {
		this.taskAllNum = taskAllNum;
	}

	public int getTaskQualifiedNum() {
		return taskQualifiedNum;
	}

	public void setTaskQualifiedNum(int taskQualifiedNum) {
		this.taskQualifiedNum = taskQualifiedNum;
	}

	public int getTaskUnQualifiedNum() {
		return taskUnQualifiedNum;
	}

	public void setTaskUnQualifiedNum(int taskUnQualifiedNum) {
		this.taskUnQualifiedNum = taskUnQualifiedNum;
	}

	public int getTaskAuditingNum() {
		return taskAuditingNum;
	}

	public void setTaskAuditingNum(int taskAuditingNum) {
		this.taskAuditingNum = taskAuditingNum;
	}

	public int getSystemType() {
		return systemType;
	}

	public void setSystemType(int systemType) {
		this.systemType = systemType;
	}
}
