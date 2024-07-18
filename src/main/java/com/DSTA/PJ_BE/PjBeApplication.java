package com.DSTA.PJ_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@PropertySource("classpath:/config/application.yml")
@SpringBootApplication
@EnableScheduling
public class PjBeApplication {
	public static void main(String[] args) {
		SpringApplication.run(PjBeApplication.class, args);
	}
}
