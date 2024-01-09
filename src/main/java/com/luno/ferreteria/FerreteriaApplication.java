package com.luno.ferreteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class FerreteriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FerreteriaApplication.class, args);
	}


}
