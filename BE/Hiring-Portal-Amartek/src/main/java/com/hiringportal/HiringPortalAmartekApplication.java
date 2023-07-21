package com.hiringportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HiringPortalAmartekApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiringPortalAmartekApplication.class, args);
	}

}
