package com.capgemini.capfoot;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.EmailService;
import com.capgemini.capfoot.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.MailException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CapfootApplication implements CommandLineRunner {

    @Autowired
    GroupService groupService;
    @Autowired
    EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(CapfootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*Groupe groupe = new Groupe(1L, "Groupe1");
        Groupe groupe2 = new Groupe(2L, "Groupe2");
        Groupe groupe3 = new Groupe(3L, "Groupe3");
        Groupe groupe4 = new Groupe(1L, "modifier");
        groupService.add(groupe);
        groupService.add(groupe2);
        groupService.add(groupe3);

        System.out.println("///////////////////////////////////////////////");
        System.out.println(groupService.findById(1L));
        groupService.findAll().forEach(g -> {
            System.out.println(g.toString());
        });

        groupService.update(Optional.of(groupe4), 1L);
        System.out.println("///////////////////////////////////////////////");
        System.out.println("Modification");
        System.out.println(groupService.findById(1L));
        groupService.findAll().forEach(g -> {
            System.out.println(g.toString());
        });
        System.out.println("///////////////////////////////////////////////");
        System.out.println("Supression");
        groupService.delete(2L);
        groupService.findAll().forEach(g -> {
            System.out.println(g.toString());
        });*/

        Player player = new Player(1l,"Salma","salma.lahoubi@capgemini.com");
        Player player1 = new Player(2L,"Hajar","hajar.sellami@capgemini.com");
        //Player player2 = new Player(3L,"Hamza","hamza.tika@capgemini.com");
        List<Player> players= new ArrayList<>();
       // players.add(player);
        players.add(player1);
        //players.add(player2);
        Team team = new Team(1L,"team1","casa",players);
       // System.out.println(team.getPlayers().get(1).getEmailAddress());

        System.out.println("Sending Email...");
        try {
            emailService.sendEmail(team,"Test","  ");
        } catch (MailException mailException) {
            mailException.getStackTrace();
        }catch(Exception ex) {
            System.out.println("Erreur d'envoie d'email: "+ex);
        }
    }

}
