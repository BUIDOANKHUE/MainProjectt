package com.ASM.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ASM.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
