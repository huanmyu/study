package com.huanyu.study.service;

import com.huanyu.study.entity.User;

public interface UserService {
	public User register(String email);

	public User checkActive(String activeCode);
}
