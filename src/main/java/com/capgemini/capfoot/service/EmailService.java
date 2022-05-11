package com.capgemini.capfoot.service;

import java.util.List;

import com.capgemini.capfoot.entity.Team;

public interface EmailService {

	public void sendEmailToAllPlayers(List<Team> teams, String subject, String message);
}
