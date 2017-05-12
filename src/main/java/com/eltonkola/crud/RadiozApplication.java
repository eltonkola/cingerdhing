package com.eltonkola.crud;

import com.eltonkola.crud.service.NewsServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class RadiozApplication {

	private static final Logger log = LoggerFactory.getLogger(RadiozApplication.class);


	@Autowired
	NewsServiceInterface mNewsServiceInterface;

	public static void main(String[] args) {
		SpringApplication.run(RadiozApplication.class, args);
	}

}
