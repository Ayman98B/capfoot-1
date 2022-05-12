package com.capgemini.capfoot.service;

import java.util.List;

import javax.mail.MessagingException;

import com.capgemini.capfoot.entity.Team;

public interface EmailService {

	public void sendEmailToAllPlayers(List<Team> teams, String subject) throws MessagingException;
	public void sendEmail(String adress, String subject)throws MessagingException;
}
