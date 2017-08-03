package com.hine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/*@MapperScan("com.hine.mapper")*/
@EnableTransactionManagement
public class SpringBootGirlStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGirlStudyApplication.class, args);
	}
}
