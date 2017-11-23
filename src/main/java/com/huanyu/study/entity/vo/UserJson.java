package com.huanyu.study.entity.vo;

import java.io.Serializable;

public class UserJson implements Serializable {

	private static final long serialVersionUID = -2618008313092297760L;
	
	private String email;
	
	protected UserJson() {
	}

	protected UserJson(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
