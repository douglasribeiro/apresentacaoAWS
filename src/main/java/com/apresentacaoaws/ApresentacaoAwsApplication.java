package com.apresentacaoaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ApresentacaoAwsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ApresentacaoAwsApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApresentacaoAwsApplication.class, args);
	}

}
