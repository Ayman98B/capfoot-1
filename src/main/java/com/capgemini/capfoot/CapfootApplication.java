package com.capgemini.capfoot;

import com.capgemini.capfoot.entity.*;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.repository.PlayerRepository;
import com.capgemini.capfoot.repository.TeamRepository;
import com.capgemini.capfoot.service.AdminService;
import com.capgemini.capfoot.service.ChampionshipService;
import com.capgemini.capfoot.service.EmailService;
import com.capgemini.capfoot.service.GroupTeamService;
import com.capgemini.capfoot.service.PlayerService;
import com.capgemini.capfoot.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;


@SpringBootApplication
public class CapfootApplication implements CommandLineRunner {


    @Autowired
    EmailService emailService;
    public static void main(String[] args) {

        SpringApplication.run(CapfootApplication.class, args);
    }

    @Override
    public void run(String... args) {}
}
