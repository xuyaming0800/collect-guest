package com.dataup.ccc.api.exception.support;

/**
 * BOSS-异常处理API基类 所有后端的异常需要继承这个基类 前端或者JOB通过这个基类获取错误信息和错误码
 * 
 * @author yaming.xu
 * 
 */
public class BaseSupportException extends RuntimeException {
	private static final long serialVersionUID = -6504714654373151868L;
	private String errorCode;
	private String status;

	public BaseSupportException() {
		super();
	}

	public BaseSupportException(Throwable cause) {
		super(cause);
	}

	public BaseSupportException(String message) {
		super(message);
	}

	public BaseSupportException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public BaseSupportException(String message, String errorCode, String status) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
