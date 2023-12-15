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
import com.ASM.entity.Category;
import com.ASM.entity.Product;
import com.ASM.entity.Report;
import com.ASM.service.CategoryService;
import com.ASM.service.FileSystemStorageService;
import com.ASM.service.ProductService;
import com.ASM.service.ReportService;

import net.bytebuddy.asm.Advice.Local;

@Controller
public class AdminCatagoryController {
	
	@Autowired
	FileSystemStorageService service;
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/admin/category")
	public String listProduct(Model model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("list", list);
		return "admin/category/index";
	}
	
	@GetMapping("/admin/editCa/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Category category = categoryService.findById(id);
		model.addAttribute("item", category);
		return "admin/category/edit";
	}
	
	@PostMapping("/admin/updateCa")
	public String editProfileProcess(Model model, @ModelAttribute("item") Category product,@PathParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			this.service.store(file);
			String fileImg =file.getOriginalFilename();
			product.setPhoto(fileImg);
		}
		categoryService.update(product);
		model.addAttribute("message", "Thông tin tài khoản của bạn đã được cập nhật");
		return "redirect:/admin/category";
	}
	
	

}