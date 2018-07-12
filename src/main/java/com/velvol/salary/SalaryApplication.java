package com.velvol.salary;

import org.mybatis.spring.annotation.MapperScan;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.velvol.salary.dao")
@SpringBootApplication
public class SalaryApplication {
	public static void main(String[] args) {
		SpringApplication.run(SalaryApplication.class, args);
	}
}
