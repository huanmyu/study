package com.huanyu.study.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huanyu.study.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
	public Permission findByName(String name);
}
