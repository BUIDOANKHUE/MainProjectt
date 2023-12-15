package com.ASM.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ASM.entity.Report;
import com.ASM.service.ReportService;

@CrossOrigin("*")
@RestController
public class ReportAPI {
	@Autowired
	ReportService reportService;
	
	@GetMapping("/api/report/revenue")
	public List<Report> getRevenueByCategory(){
		return reportService.getRevenueByCategory();
	}
}