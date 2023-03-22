package com.kenny.gsbatchprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @SpringBootApplication은 편의 어노테이션
 *     - @Configuration 포함
 *     - @EnableAutoConfiguration 포함
 *     - @ComponentScan 포함
 */
@SpringBootApplication
public class GsBatchProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsBatchProcessingApplication.class, args);
	}

}
