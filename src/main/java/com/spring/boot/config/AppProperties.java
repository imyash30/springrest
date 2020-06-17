package com.spring.boot.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
//@PropertySource("classpath:app.properties")
public class AppProperties {

	@Autowired
	Environment env;
	
	private Map<String, String> properties = new HashMap<>();
	
	@PostConstruct
	public void init() {
		properties.put("database.url", env.getProperty("database.url"));
		properties.put("database.driverclassname", env.getProperty("database.driverclassname"));
		properties.put("database.username", env.getProperty("database.username"));
		properties.put("database.password", env.getProperty("database.password"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
	}
	
	public String getProperty(String propertyName) {
//		return properties.get(propertyName);
		return env.getProperty(propertyName);
	}
	
	public Map<String, String> getAllProperties() {
		return properties;
	}
}
