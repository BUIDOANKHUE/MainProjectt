package com.ASM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ASM.entity.Product;
import com.ASM.service.FileSystemStorageService;
import com.ASM.service.ProductService;

@Controller
public class HomeController {
	
	
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/")
	public String index(Model model,@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Product> list = productService.findAll(pageNo);
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("items", list);
		return "home/index";
	}
	@RequestMapping("/home/about")
	public String about() {
		return "home/about";
	}
	@RequestMapping("/home/contact")
	public String contact() {
		return "home/contact";
	}
	@RequestMapping("/home/feedback")
	public String feedback() {
		return "home/feedback";
	}
	@RequestMapping("/home/faq")
	public String faq() {
		return "home/faq";
	}
//	@RequestMapping({"/admin","/admin/home/index"})
//	public String admin() {
//		return "redirect:/admin/index.html";
//	}

}