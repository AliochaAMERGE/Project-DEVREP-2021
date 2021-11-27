package fr.su.bankapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BankappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankappApplication.class, args);
	}
}