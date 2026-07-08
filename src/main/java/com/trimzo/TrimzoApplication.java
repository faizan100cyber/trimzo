package com.trimzo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TrimzoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrimzoApplication.class, args);
	}

}
