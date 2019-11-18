package com.IsaPsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@EntityScan(basePackages = {"model"})  
@SpringBootApplication

public class ProjekatIsaPswApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjekatIsaPswApplication.class, args);
	}

}
