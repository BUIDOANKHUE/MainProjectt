package com.ASM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ASM.entity.Product;
import com.ASM.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/product/list-by-category/{id}")
	public String listByCategory(Model model, @PathVariable("id") Integer id) {
		List<Product> list = productService.findByCategoryId(id);
		model.addAttribute("items", list);
		return "product/listCata";
	}
	
	
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		model.addAttribute("item", product);
		return "product/detail";
	}
	
	@RequestMapping("/product/sort-by-price")
	public String sortsort(Model model) {
		List<Product> producList = productService.findAllByOrderByPriceDesc();
		model.addAttribute("items", producList);
		
		return "product/list";
	}
	
	@RequestMapping("/product/sort-by-price-100000")
	public String sorT1(Model model) {
		List<Product> producList = productService.findByprice100000();
		model.addAttribute("items", producList);
		
		return "product/list";
	}
	@RequestMapping("/product/sort-by-price-200000")
	public String sort2(Model model) {
		List<Product> producList = productService.findByprice200000();
		model.addAttribute("items", producList);
		
		return "product/list";
	}
	@RequestMapping("/product/sort-by-price-300000")
	public String sort(Model model) {
		List<Product> producList = productService.findByprice300000();
		model.addAttribute("items", producList);
		
		return "product/list";
	}
	
	@RequestMapping("/product/sort-by-price-400000")
	public String sort4(Model model) {
		List<Product> producList = productService.findByprice400000();
		model.addAttribute("items", producList);
		
		return "product/list";
	}

	// sắp xếp giá từ thấp lên cao
	@RequestMapping("/product/sort-by-priceAsce")
	public String sortlow(Model model) {
		List<Product> productList = productService.findAllByOrderByPriceAsc();
		model.addAttribute("items", productList);
		
		return "product/list";
	}
	
	@RequestMapping("/product/search")
	public String search (Model model, @Param("keyword") String keyword,@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Product> list = productService.findAll(pageNo);
		
		if (keyword!=null) {
			list=productService.findByKeyWordsAndPages(keyword,pageNo);
			model.addAttribute("keyword", keyword);
		}
		
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("items", list);
		return "product/listSearch";
	}
}