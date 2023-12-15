package com.ASM.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ASM.entity.Account;
import com.ASM.entity.Product;
import com.ASM.entity.Report;
import com.ASM.service.FileSystemStorageService;
import com.ASM.service.ProductService;
import com.ASM.service.ReportService;

import net.bytebuddy.asm.Advice.Local;

@Controller
public class AdminReportController {
	
	
	
	@Autowired
	ReportService reportService;
	
	
	
	@RequestMapping("/admin/report")
	public String listProduct(Model model) {
		List<Report> report =reportService.getRevenueByCategory();
		model.addAttribute("report", report);
		return "admin/report/index";
	}
	
	

}