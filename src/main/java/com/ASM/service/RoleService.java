package com.ASM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ASM.dao.RoleDAO;

import com.ASM.entity.Role;

@Service
public class RoleService {
	@Autowired
	RoleDAO dao;
	
	public List<Role> findAll() {
		return dao.findAll();
	}
}
