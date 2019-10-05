package com.retail.processor.retailPriceProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class RetailPriceProcessorApplication {

	private static final Logger logger = LoggerFactory.getLogger(RetailPriceProcessorApplication.class);
		
	public static void main(String[] args) {		
		logger.info("Application is starting	...........");
		SpringApplication.run(RetailPriceProcessorApplication.class, args);
	}	

}
