package com.ASM.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ASM.dao.AccountDAO;
import com.ASM.entity.Account;

@Service
public class AccountService {
	@Autowired
	AccountDAO dao;

	public void create(Account account) {
		dao.save(account);
	}

	 public Optional<Account> findByUsername(String username) {
	        return dao.findByUsername(username);
	    }

	 public Optional<Account> findByEmail(String email) {
	        return dao.findByemail(email);
	    }
	 public Optional<Account> findByResetToken(String resetToken) {
	        return dao.findByResetToken(resetToken);
	    }
	
	public Account findById(String username) {
		return dao.findById(username).get();
	}
	
	public void update(Account account) {
		dao.save(account);
	}

	public List<Account> findMasters() {
		return dao.getMasters();
	}

	public boolean isUsernameExists(String username) {
	    Optional<Account> existingAccount = findByUsername(username);
	    return existingAccount.isPresent();
	}
}
