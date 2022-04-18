package com.capgemini.capfoot;

import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Optional;

@SpringBootApplication
public class CapfootApplication implements CommandLineRunner {

	@Autowired
	GroupService groupService;

	public static void main(String[] args) {
		SpringApplication.run(CapfootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Groupe groupe = new Groupe(1L,"Groupe1");
		Groupe groupe2 = new Groupe(2L,"Groupe2");
		Groupe groupe3 = new Groupe(3L,"Groupe3");
		Groupe groupe4 = new Groupe(1L,"modifier");
		groupService.add(groupe);
		groupService.add(groupe2);
		groupService.add(groupe3);

		System.out.println("///////////////////////////////////////////////");
		System.out.println(groupService.findById(1L));
		groupService.findAll().forEach(g->{
			System.out.println(g.toString());
		});

		groupService.update(Optional.of(groupe4),1L);
		System.out.println("///////////////////////////////////////////////");
		System.out.println("Modification");
		System.out.println(groupService.findById(1L));
		groupService.findAll().forEach(g->{
			System.out.println(g.toString());
		});
		System.out.println("///////////////////////////////////////////////");
		System.out.println("Supression");
		groupService.delete(2L);
		groupService.findAll().forEach(g->{
			System.out.println(g.toString());
		});
	}

}
