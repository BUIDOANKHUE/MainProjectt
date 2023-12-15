package com.ASM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		
	
		http.authorizeRequests()
		.antMatchers("/account/change-password",
				"/account/edit-profile",
				"/order/**",
				"/security/logout",
				"/index.html/**").authenticated()
		.antMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
		.anyRequest().permitAll();
		
		http.exceptionHandling()
			.accessDeniedPage("/security/access/denied");
		
		
		http.formLogin()
			.loginPage("/security/login/form")
			.loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/security/login/success")
			.failureUrl("/security/login/failure");
		
		http.logout()
			.logoutUrl("/security/logout")
			.logoutSuccessUrl("/security/logout/success")
			.addLogoutHandler(new SecurityContextLogoutHandler())
			.clearAuthentication(true);
		
		//OAuth2- đăng nhập từ mạng xã hội
				http.oauth2Login()
				.loginPage("/security/login/form")
				.defaultSuccessUrl("/",true)
				.failureUrl("/security/login/failure")
				.authorizationEndpoint()
					.baseUri("/oauth2/authorization");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/rsx/**", "/api/**");
	}
	
	
}
