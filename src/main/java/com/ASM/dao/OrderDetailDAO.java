package com.ASM.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ASM.entity.OrderDetail;
import com.ASM.entity.Report;


public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
	@Query("SELECT d.product.category.name AS category, "
			+ " SUM(d.quantity*d.price) AS totalAmount, "
			+ " SUM(d.quantity) AS totalCount, "
			+ " MIN(d.price) AS minPrice, "
			+ " MAX(d.price) AS maxPrice, "
			+ " AVG(d.price) AS averagePrice "
			+ "FROM OrderDetail d "
			+ "GROUP BY d.product.category.name")
	List<Report> getRevenueByCategory();
}
