package com.ASM.controller;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ASM.CaptchaGenerator;
import com.ASM.entity.Account;
import com.ASM.entity.Product;
import com.ASM.service.AccountService;
import com.ASM.service.FileSystemStorageService;

import cn.apiclub.captcha.Captcha;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	FileSystemStorageService service;
	@Autowired
	PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

	@GetMapping("/account/sign-up")
	public String signUpForm(@ModelAttribute("account") Account account, Model model) {
		
		getCaptcha(account);
		model.addAttribute("account", account);
		
		return "account/sign-up";
	}
	@PostMapping("/account/sign-up")
	public String signUpProcess(Model model, @ModelAttribute("account") Account account,
	        @RequestParam("confirm") String confirm, @RequestParam("file") MultipartFile file) {
	    if (confirm.equals(account.getPassword()) && account.getCaptcha().equals(account.getHiddenCaptcha())) {
	        // Mật khẩu và Captcha đều chính xác
	        if (!accountService.isUsernameExists(account.getUsername())) {
	            // Kiểm tra xem tên tài khoản đã tồn tại chưa
	            this.service.store(file);
	            String fileImg = file.getOriginalFilename();
	            account.setPhoto(fileImg);
	            accountService.create(account);
	            getCaptcha(account);
	            model.addAttribute("message", "Đăng ký thành công");
	        } else {
	            getCaptcha(account);
	            model.addAttribute("message", "Tên tài khoản đã tồn tại");
	        }
	    } else {
	        getCaptcha(account);
	        model.addAttribute("message", "Xác nhận mật khẩu hoặc Captcha không chính xác");
	    }
	    return "account/sign-up";
	}

	private void getCaptcha(Account account) {
		Captcha captcha = CaptchaGenerator.generateCaptcha(240, 70);
		account.setHiddenCaptcha(captcha.getAnswer());
		account.setCaptcha(""); // nhập bởi khách hàng
		account.setRealCaptcha(CaptchaGenerator.encodeCaptchatoBinary(captcha));
	}
	
	@GetMapping("/account/edit-profile")
	public String editProfileForm(Model model, Authentication auth) {
		String username = auth.getName();
		Account account = accountService.findById(username);
		model.addAttribute("account", account);
		return "account/edit-profile";
	}

	@PostMapping("/account/edit-profile")
	public String editProfileProcess(Model model, @ModelAttribute("account") Account account,@PathParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			this.service.store(file);
			String fileImg =file.getOriginalFilename();
			account.setPhoto(fileImg);
		}
		accountService.update(account);
		model.addAttribute("message", "Thông tin tài khoản của bạn đã được cập nhật");
		return "account/edit-profile";
	}

	@GetMapping("/account/forgot-password")
	public String forgotPasswordForm() {
		return "account/forgot-password";
	}

	@PostMapping("/account/forgot-password")
	public String forgotPasswordProcess(Model model, @RequestParam("email") String email,
			HttpServletRequest request) {
		Optional<Account> userOptional = accountService.findByEmail(email);
		if (userOptional.isPresent()) {
            Account user = userOptional.get();
            String token = UUID.randomUUID().toString();
            user.setResettoken(token);
            accountService.create(user);

            String resetUrl = "http://" + request.getServerName() + ":" + request.getServerPort() +
                    request.getContextPath() + "/reset-password?token=" + token;

            // Gửi email với liên kết đặt lại mật khẩu (cần triển khai phương thức này)
            sendResetEmail(user.getEmail(), resetUrl);
            
            return "redirect:/security/login/form";
        }else {
            // Email không tồn tại trong hệ thống
            return "redirect:/account/forgot-password?error";
        }
	
	}
	private void sendResetEmail(String to, String resetUrl) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Yêu cầu đặt lại mật khẩu");
        message.setText("Để đặt lại mật khẩu, hãy nhấp vào liên kết dưới đây:\n" + resetUrl );
        javaMailSender.send(message);
    }
	
	@GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        Optional<Account> userOptional = accountService.findByResetToken(token);

        if (userOptional.isPresent()) {
            model.addAttribute("token", token);
            return "account/reset-password";
        } else {
            // Token không hợp lệ
            return "redirect:/login?resetError";
        }
    }
	@PostMapping("/reset-password")
    public String processResetPasswordForm(@RequestParam("token") String token, @RequestParam("password") String password) {
        Optional<Account> userOptional = accountService.findByResetToken(token);

        if (userOptional.isPresent()) {
            Account user = userOptional.get();
            // Cập nhật mật khẩu người dùng
            user.setPassword(password);
            user.setResettoken(null);
            accountService.update(user);

            return "redirect:/security/login/form";
        } else {
            // Token không hợp lệ
            return "redirect:/login?resetError";
        }
    }
	
	@GetMapping("/account/change-password")
	public String changePasswordForm(Model model, Authentication auth) {
		String username = auth.getName();
		Account account = accountService.findById(username);
		model.addAttribute("account", account);
		return "account/change-password";
	}

	@PostMapping("/account/change-password")
	public String changePasswordProcess(Model model,@ModelAttribute("account") Account account, @RequestParam("username") String username,
			@RequestParam("oldpass") String oldpass, @RequestParam("newpass") String newpass,
			@RequestParam("confirm") String confirm) {
		if(!oldpass.equals(account.getPassword())) {
			model.addAttribute("message", "Password ko chính xác");
		} else {
			if (!confirm.equals(newpass)) {
				model.addAttribute("message", "Xác nhận password mới ko chính xác");
			}else {
				account.setPassword(newpass);
				accountService.update(account);
				model.addAttribute("message", "Thay đổi password thành công");
			}
			
		}
		return "account/change-password";
	}
	
}