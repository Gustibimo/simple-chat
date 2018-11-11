package com.gustibimo.simplechat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//@EnableAutoConfiguration
//@ComponentScan
//@Configuration
@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class SimpleChatApplication {
	@Value("${api.path}")
	private String versionpath;


	public static void main(String[] args) {
		SpringApplication.run(SimpleChatApplication.class, args);
	}	
}
