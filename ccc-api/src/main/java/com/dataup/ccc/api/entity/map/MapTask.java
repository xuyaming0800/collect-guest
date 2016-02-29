package com.dataup.ccc.api.entity.map;

import java.io.Serializable;

public class MapTask implements Serializable {
 
	private static final long serialVersionUID = -7349257353315577103L;
	private String taskId;// 任务id
	private double lon;// 坐标x
	private double lat;// 坐标y
	private String taskClassName;// 任务类别
	private Integer status;// 状态
	private Integer systemType;// 系统类型
	private int id = 1;// id
	

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getTaskClassName() {
		return taskClassName;
	}

	public void setTaskClassName(String taskClassName) {
		this.taskClassName = taskClassName;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getSystemType() {
		return systemType;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	@Override
	public String toString() {
		return "MapTask [taskId=" + taskId + ", lon=" + lon + ", lat=" + lat
				+ ", taskClassName=" + taskClassName + ", status=" + status
				+ ", systemType=" + systemType + ", id=" + id + "]";
	}

}
