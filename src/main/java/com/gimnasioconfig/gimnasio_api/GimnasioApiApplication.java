package com.gimnasioconfig.gimnasio_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GimnasioApiApplication {

	public static void main(String[] args) {

		System.out.println(new BCryptPasswordEncoder().encode("passi"));

		SpringApplication.run(GimnasioApiApplication.class, args);
	}

}
