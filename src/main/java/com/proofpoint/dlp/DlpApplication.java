package com.proofpoint.dlp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:dlp-ctx.xml")
public class DlpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DlpApplication.class, args);
	}
}
