package com.ASM.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ASM.entity.Account;
import com.ASM.service.AccountService;

@CrossOrigin("*")
@RestController
public class MasterAPI {
	@Autowired
	AccountService accountService;
	
	@GetMapping("/api/masters")
	public List<Account> getMaster(){
		return accountService.findMasters();
	}
	
	
	
}