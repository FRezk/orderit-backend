package com.rezk.orderit.mc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rezk.orderit.mc.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String dbStrategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (dbStrategy.contains("create")) {
			dbService.instantiateTestDatabase();
		}
		return true;
	}

}
