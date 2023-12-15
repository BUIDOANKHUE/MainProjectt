package com.ASM.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ASM.entity.Authority;
import com.ASM.service.AuthorityService;

@CrossOrigin("*")
@RestController
public class AuthorityAPI {
	@Autowired
	AuthorityService authorityService;
	
	@GetMapping("/api/authorities")
	public List<Authority> getAll(){
		return authorityService.findAll();
	}
	@PostMapping("/api/authorities")
	public Authority create(@RequestBody Authority authority){
		return authorityService.create(authority);
	}
	@DeleteMapping("/api/authorities/{id}")
	public void delete(@PathVariable("id") Integer id){
		authorityService.deleteById(id);
	}
}