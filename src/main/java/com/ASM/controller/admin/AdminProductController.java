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
import com.ASM.service.FileSystemStorageService;
import com.ASM.service.ProductService;

import net.bytebuddy.asm.Advice.Local;

@Controller
public class AdminProductController {
	
	@Autowired
	FileSystemStorageService service;
	
	@Autowired
	ProductService productService;
	
	
	@RequestMapping({"/admin","/admin/home/index"})
	public String admin() {
		return "admin/index";
	}
	@RequestMapping("/admin/product")
	public String listProduct(Model model) {
		List<Product> productList = productService.findAll();
		model.addAttribute("list", productList);
		return "admin/product/index";
	}
	@GetMapping("/admin/editPd/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		model.addAttribute("item", product);
		return "admin/product/edit";
	}
	@PostMapping("/admin/updatePr")
	public String editProfileProcess(Model model, @ModelAttribute("item") Product product,@PathParam("file") MultipartFile file,@PathParam("date") String date) throws ParseException {
		if (!file.isEmpty()) {
			this.service.store(file);
			String fileImg =file.getOriginalFilename();
			product.setImage(fileImg);
		}
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 Date d =dateFormat.parse(date); 
		 product.setCreateDate(d);
		productService.update(product);
		model.addAttribute("message", "Thông tin tài khoản của bạn đã được cập nhật");
		return "redirect:/admin/product";
	}
	@GetMapping("/admin/createPr")
	public String viewCreate(Model model) {
		
		Product product = new Product();		
		product.setAvailable(true);
		product.setCreateDate(new Date());
		model.addAttribute("item", product);
		return "admin/product/create";
	}
	@PostMapping("/admin/createPr")
	public String CreateProfileProcess(Model model, @ModelAttribute("item") Product product,@PathParam("file") MultipartFile file,@PathParam("date") String date) throws ParseException {
		if (!file.isEmpty()) {
			this.service.store(file);
			String fileImg =file.getOriginalFilename();
			product.setImage(fileImg);
		}
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 Date d =dateFormat.parse(date); 
		 product.setCreateDate(d);
		productService.update(product);
		model.addAttribute("message", "Thông tin tài khoản của bạn đã được cập nhật");
		return "redirect:/admin/product";
	}
	@RequestMapping("/admin/deletePd/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		productService.deleteById(id);
		return "redirect:/admin/product";
	}
	

}