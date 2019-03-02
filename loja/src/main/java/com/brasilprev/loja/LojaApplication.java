package com.brasilprev.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = { "com.brasilprev.loja" }, exclude = { SecurityAutoConfiguration.class })
public class LojaApplication {

	@RequestMapping("/home")
	public String hello() {
		return "Hello buddy!";
	}

	public static void main(String[] args) {
		SpringApplication.run(LojaApplication.class, args);
	}

}
