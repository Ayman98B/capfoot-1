package com.capgemini.capfoot.service;

import java.util.List;

import javax.mail.MessagingException;

import com.capgemini.capfoot.entity.Championship_State;
import com.capgemini.capfoot.entity.Team;

public interface EmailService {

	public void sendEmailAfterInscription(Team team) throws MessagingException;
	public void sendEmailQualifiedTeams(List<Team> teams, Championship_State champSt) throws MessagingException;
	public void sendEmailChampNewsToAll(List<Team> teams, Championship_State champSt) throws MessagingException;
}
