package com.dataup.ccc.api.entity;

public class ResultEntity implements java.io.Serializable {

	private static final long serialVersionUID = 883511084525128741L;

	public ResultEntity() {

	}

	/**
	 * 是否成功
	 */
	private boolean success;

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 返回信息
	 */
	private Object info;
	
	/**
	 * 多对象的时候使用
	 */
	private Object otherInfo;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public Object getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(Object otherInfo) {
		this.otherInfo = otherInfo;
	}

	@Override
	public String toString() {
		return "ResultEntity [success=" + success + ", code=" + code
				+ ", desc=" + desc + ", info=" + info + ", otherInfo="
				+ otherInfo + "]";
	}

}
