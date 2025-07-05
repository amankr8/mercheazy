package com.mercheazy.cloudinary_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CloudinaryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudinaryServiceApplication.class, args);
	}

}
