package com.ASM.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ASM.entity.Role;
import com.ASM.service.RoleService;

@CrossOrigin("*")
@RestController
public class RoleAPI {
	@Autowired
	RoleService roleService;
	
	@GetMapping("/api/roles")
	public List<Role> getAll(){
		return roleService.findAll();
	}
	
}