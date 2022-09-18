package com.medaSolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GestionScolariteApplication {

	
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String mdp = bc.encode("aqwzsxedc123");
		System.out.println("encoded password : "+ mdp );
		SpringApplication.run(GestionScolariteApplication.class, args);
	}

}
