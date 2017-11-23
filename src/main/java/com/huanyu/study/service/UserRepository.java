package com.huanyu.study.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huanyu.study.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
}
