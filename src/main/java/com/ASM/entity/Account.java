package com.ASM.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@SuppressWarnings("serial")
@Entity 
@Table(name = "Accounts")
public class Account  implements Serializable{

	@Id
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String photo;
	private String resettoken;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;

	@Transient
	private String captcha;
	@Transient
	private String hiddenCaptcha;
	@Transient
	private String realCaptcha;
	public Account() {
		super();
	}
	
	public Account(String username, String password, String fullname, String email, String photo, String resettoken,
			List<Authority> authorities, List<Order> orders, String captcha, String hiddenCaptcha, String realCaptcha) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.photo = photo;
		this.resettoken = resettoken;
		this.authorities = authorities;
		this.orders = orders;
		this.captcha = captcha;
		this.hiddenCaptcha = hiddenCaptcha;
		this.realCaptcha = realCaptcha;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getHiddenCaptcha() {
		return hiddenCaptcha;
	}
	public void setHiddenCaptcha(String hiddenCaptcha) {
		this.hiddenCaptcha = hiddenCaptcha;
	}
	public String getRealCaptcha() {
		return realCaptcha;
	}
	public void setRealCaptcha(String realCaptcha) {
		this.realCaptcha = realCaptcha;
	}
	public String getResettoken() {
		return resettoken;
	}
	public void setResettoken(String resettoken) {
		this.resettoken = resettoken;
	}
	
	
}
