package com.deepak.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EcomEurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomEurekaServiceApplication.class, args);
	}

}
