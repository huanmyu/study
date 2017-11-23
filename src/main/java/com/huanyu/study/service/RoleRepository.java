package com.huanyu.study.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huanyu.study.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByName(String name);
}
