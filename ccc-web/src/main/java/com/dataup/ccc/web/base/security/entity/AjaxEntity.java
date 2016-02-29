package com.dataup.ccc.web.base.security.entity;

import com.dataup.ccc.api.util.SysConstant;

public class AjaxEntity<T> {

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	private boolean success = false;
	private String errors = null;
	private String code = SysConstant.FAULT_CODE;
	private String desc = "失败";
	private T info;
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public AjaxEntity() {
	}

	public AjaxEntity(boolean initStatus) {
		if (initStatus) {
			this.success = true;
			this.code = SysConstant.SUCCESS_CODE;
			this.desc = "成功";
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

}
