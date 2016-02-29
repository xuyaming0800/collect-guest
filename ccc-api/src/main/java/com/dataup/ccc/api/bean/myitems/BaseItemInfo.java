package com.dataup.ccc.api.bean.myitems;

import java.io.Serializable;

public class BaseItemInfo implements Serializable {

	private static final long serialVersionUID = -3475727279652705072L;

	private int taskAllNum;
	private int taskQualifiedNum;
	private int taskUnQualifiedNum;
	private int taskAuditingNum;
	private int systemType;

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
