package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;



    @Service
    public class SendEmailImp implements  Send {

        @Autowired
        public JavaMailSender emailSender;

        public void sendEmail(Team toAddress, String subject, String message) {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            for(int i=0;i< toAddress.getPlayers().size();i++){
                simpleMailMessage.setTo(String.valueOf(toAddress.getPlayers().get(i)));
                simpleMailMessage.setSubject(subject);
                simpleMailMessage.setText(message);
                emailSender.send(simpleMailMessage);
                System.out.println("mail envoyée");}
        }

}
