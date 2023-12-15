package com.ASM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ASM.service.FileSystemStorageService;

@Controller
@RequestMapping("/upload-test")
public class testController {
	
	@Autowired
	FileSystemStorageService service;
	
	@GetMapping
	public String uploadDemo() {
		return "test";
	}
	@PostMapping
	public String save(@RequestParam("file") MultipartFile file) {
		this.service.store(file);
		
		return "test";
	}
}
