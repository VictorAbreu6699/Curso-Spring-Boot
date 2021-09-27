package com.victor.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.victor.services.DBService;

@Configuration
@Profile("test")
public class TesteConfig {

	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		dbService.instantiateTesteDataBase();
		return true;
	}
}