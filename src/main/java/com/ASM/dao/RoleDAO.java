package com.ASM.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ASM.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String> {

}
