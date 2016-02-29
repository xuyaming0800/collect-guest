package com.dataup.ccc.web.exception;

import org.springframework.web.multipart.MultipartException;

@SuppressWarnings("serial")
public class FileTypeNotAllowException extends MultipartException {
	
	private String[] extAllowed;
	
	public String[] getExtAllowed() {
		return extAllowed;
	}
	public void setExtAllowed(String[] extAllowed) {
		this.extAllowed = extAllowed;
	}

	public FileTypeNotAllowException(String[] extAllowed,String msg) {
		super(msg);
		this.extAllowed = extAllowed;
	}
}
