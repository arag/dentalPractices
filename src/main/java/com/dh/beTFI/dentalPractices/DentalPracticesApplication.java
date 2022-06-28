package com.dh.beTFI.dentalPractices;

import com.dh.beTFI.dentalPractices.repository.h2Aux;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DentalPracticesApplication {

	public static void main(String[] args) {
		h2Aux.setUpConnection();
		SpringApplication.run(DentalPracticesApplication.class, args);
	}

}
