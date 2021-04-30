package com.crm.sample.api;

import javax.servlet.Filter;
import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 簡單Spring boot 展示
 * @author alex cheng
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@EntityScan("com.crm.sample.repository.model")
@EnableJpaRepositories("com.crm.sample.repository")
public class CrmApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}
	
	@Bean 
	public HttpSessionListener javaMelodyListener(){ 
	    return new net.bull.javamelody.SessionListener(); 
	} 

	@Bean 
	public Filter javaMelodyFilter(){ 
	    return new net.bull.javamelody.MonitoringFilter(); 
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
