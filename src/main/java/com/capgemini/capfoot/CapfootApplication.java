package com.capgemini.capfoot;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CapfootApplication implements CommandLineRunner{
	@Autowired
	private PlayerService playerService;

	public static void main(String[] args) {
		SpringApplication.run(CapfootApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}



}
