package com.ASM.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ASM.entity.Category;
import com.ASM.service.CategoryService;

@CrossOrigin("*")
@RestController
public class CategoryAPI {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/api/categories")
	public List<Category> getAll(){
		return categoryService.findAll();
	}
	@GetMapping("/api/categories/{id}")
	public Category getOne(@PathVariable("id") Integer id){
		return categoryService.findById(id);
	}
	@PostMapping("/api/categories")
	public Category create(@RequestBody Category category){
		categoryService.create(category);
		return category;
	}
	@PutMapping("/api/categories/{id}")
	public Category update(@RequestBody Category category, 
			@PathVariable("id") Integer id){
		if(categoryService.existedById(id)) {
			categoryService.update(category);
		} else {
			throw new RuntimeException();
		}
		return category;
	}
	@DeleteMapping("/api/categories/{id}")
	public void delete(@PathVariable("id") Integer id){
		categoryService.deleteById(id);
	}
}