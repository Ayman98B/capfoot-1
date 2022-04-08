package com.capgemini.capfoot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capgemini.capfoot.entity.Match;
import com.capgemini.capfoot.service.MatchService;


@SpringBootApplication

public class CapfootApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CapfootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
