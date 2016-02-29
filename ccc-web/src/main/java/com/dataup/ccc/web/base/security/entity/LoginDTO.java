package com.dataup.ccc.web.base.security.entity;

public class LoginDTO {
	String id=null;
	String username = null;
	String password = null;
	String validatecode = null;
	boolean isRemember=false;

	public boolean getIsRemember() {
		return isRemember;
	}

	public void setRemember(boolean isRemember) {
		this.isRemember = isRemember;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [username=" + username + ", password=" + password
				+ ", validatecode=" + validatecode + "]";
	}

	public String getValidatecode() {
		return validatecode;
	}

	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
	}

}
