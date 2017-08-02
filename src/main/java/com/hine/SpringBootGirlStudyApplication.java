package com.hine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hine.mapper")
public class SpringBootGirlStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGirlStudyApplication.class, args);
	}
}
