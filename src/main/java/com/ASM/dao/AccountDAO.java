package com.ASM.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ASM.entity.Account;


public interface AccountDAO extends JpaRepository<Account, String> {
	@Query("SELECT a FROM Account a WHERE a.authorities IS NOT EMPTY")
	List<Account> getMasters();
	
	@Query("SELECT a FROM Account a WHERE a.email=?1")
	Optional<Account> findByemail(String email);
	
	@Query("SELECT a FROM Account a WHERE a.username=?1")
	Optional<Account> findByUsername(String user);
	
	@Query("SELECT a FROM Account a WHERE a.resettoken=?1")
	Optional<Account> findByResetToken(String resetToken);
}
