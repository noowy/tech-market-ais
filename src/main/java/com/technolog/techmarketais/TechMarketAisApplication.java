package com.technolog.techmarketais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class TechMarketAisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechMarketAisApplication.class, args);
	}

}
