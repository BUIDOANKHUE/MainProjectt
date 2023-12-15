package com.ASM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ASM.dao.OrderDetailDAO;
import com.ASM.entity.Report;

@Service
public class ReportService {
	@Autowired
	OrderDetailDAO dao;
	
	public List<Report> getRevenueByCategory(){
		return dao.getRevenueByCategory();
	}
}