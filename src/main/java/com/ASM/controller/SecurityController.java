package com.ASM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ASM.entity.Product;
import com.ASM.service.ProductService;

@Controller
public class SecurityController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/security/login/form")
	public String loginForm() {
		return "security/login";
	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		
		model.addAttribute("message", "Đăng nhập thành công");
		return "redirect:/";
	}
	
	@RequestMapping("/security/login/failure")
	public String loginFailure(Model model) {
		model.addAttribute("message", "Đăng nhập thất bại");
		return "security/login";
	}
	
	@RequestMapping("/security/access/denied")
	public String accessDenied(Model model) {
		model.addAttribute("message", "Không có thẩm quyền truy cập");
		return "security/login";
	}
	
	@RequestMapping("/security/logout/success")
	public String logoutSuccess(Model model) {
		model.addAttribute("message", "Đăng xuất thành công");
		return "security/login";
	}
}