package com.ASM;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ASM.service.FileSystemStorageService;

@SpringBootApplication
public class Asm3Application {

	public static void main(String[] args) {
		SpringApplication.run(Asm3Application.class, args);
	}
	
	@Bean
	CommandLineRunner init(FileSystemStorageService storageService) {
		return (args) -> {
			
			storageService.init();
		};
	}
}
