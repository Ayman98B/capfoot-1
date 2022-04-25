package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

    @Service
    public class EmailServiceImp implements  EmailService {

        @Autowired
        public JavaMailSender emailSender;

        public void sendEmail(Team toAddress, String subject, String message) {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            for(int i=0;i< toAddress.getPlayers().size();i++){
                if(toAddress.getPlayers().get(i).isCaptain())
                simpleMailMessage.setTo(String.valueOf(toAddress.getPlayers().get(i).getEmailAddress()));
                if(toAddress.getPlayers().get(i).isCaptain()==false)
                simpleMailMessage.setCc(String.valueOf(toAddress.getPlayers().get(i).getEmailAddress()));
                simpleMailMessage.setSubject(subject);}
                simpleMailMessage.setText(message);
                emailSender.send(simpleMailMessage);
               // System.out.println("mail envoyée a "+toAddress.getPlayers().get(i).getFirstName()+" "+toAddress.getPlayers().get(i).getLastName());
        }

}
