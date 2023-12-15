package com.ASM.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ASM.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(Integer id);
	@Query("SELECT p FROM Product p WHERE p.price < 100000")
	List<Product> findByPrice100000();
	
	@Query("SELECT p FROM Product p WHERE p.price BETWEEN 100000 AND 200000")
	List<Product> findByPrice200000();
	
	@Query("SELECT p FROM Product p WHERE p.price BETWEEN 200000 AND 300000")
	List<Product> findByPrice300000();
	@Query("SELECT p FROM Product p WHERE p.price > 300000")
	List<Product> findByPrice400000();
	
	@Query("SELECT p FROM Product p WHERE p.nxb=?1")
	List<Product> findBynxb(String nxb);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
	List<Product> findByKeywords(String keyword);
	
	List<Product> findAllByOrderByPriceDesc();



	List<Product> findAllByOrderByPriceAsc();
}
